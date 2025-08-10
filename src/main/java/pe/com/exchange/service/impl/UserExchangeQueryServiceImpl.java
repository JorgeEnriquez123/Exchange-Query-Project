package pe.com.exchange.service.impl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pe.com.exchange.dto.ExchangeRateQuery;
import pe.com.exchange.dto.UserExchangeQueryAttempts;
import pe.com.exchange.model.entity.UserExchangeQuery;
import pe.com.exchange.proxy.ExchangeRateApi;
import pe.com.exchange.proxy.model.ExchangeRate;
import pe.com.exchange.repository.UserExchangeQueryRepository;
import pe.com.exchange.service.UserExchangeQueryService;
import pe.com.exchange.utils.exceptions.InvalidDniException;
import pe.com.exchange.utils.exceptions.QueryLimitExceededException;
import pe.com.exchange.utils.exceptions.UserNotFoundException;
import pe.com.exchange.utils.mapper.ExchangeRateMapper;

import java.util.List;
import java.util.Optional;

import static pe.com.exchange.utils.constants.Constants.INITIAL_QUERY_ATTEMPTS;
import static pe.com.exchange.utils.constants.Constants.MAX_QUERY_ATTEMPTS_PER_USER;

@Slf4j
@ApplicationScoped
@RequiredArgsConstructor
public class UserExchangeQueryServiceImpl implements UserExchangeQueryService {
    private final UserExchangeQueryRepository userExchangeQueryRepository;
    private final ExchangeRateApi exchangeRateApi;

    @Override
    public List<UserExchangeQueryAttempts> getAllUserExchangeQueries() {
        return userExchangeQueryRepository.listAll()
                .stream()
                .map(ExchangeRateMapper.INSTANCE::toUserExchangeQueryAttempts)
                .toList();
    }

    @Override
    @Transactional
    public ExchangeRateQuery getExchangeRateQuery(String dni) {
        validateDni(dni);
        UserExchangeQuery userQuery = findUserQueryByDniOrCreate(dni);
        checkQueryAttempts(userQuery, dni);
        return fetchAndMapExchangeRate(userQuery);
    }

    @Override
    public UserExchangeQueryAttempts getUserExchangeQueryAttempts(String dni) {
        validateDni(dni);
        UserExchangeQuery userQuery = userExchangeQueryRepository.findByDni(dni)
                .orElseThrow(() -> new UserNotFoundException("User with DNI " + dni + " not found"));
        return ExchangeRateMapper.INSTANCE.toUserExchangeQueryAttempts(userQuery);
    }

    private void validateDni(String dni) {
        Optional.ofNullable(dni)
                .filter(d -> d.matches("\\d{8}"))
                .orElseThrow(() -> {
                    log.error("DNI is null, empty, or invalid (must be exactly 8 digits)");
                    return new InvalidDniException("DNI must be exactly 8 numeric digits");
                });
    }

    private UserExchangeQuery findUserQueryByDniOrCreate(String dni) {
        return userExchangeQueryRepository.findByDni(dni)
                .orElseGet(() -> createAndPersistUserQuery(dni));
    }

    private UserExchangeQuery createAndPersistUserQuery(String dni) {
        UserExchangeQuery userQuery = UserExchangeQuery.builder()
                .dni(dni)
                .queryAttempts(INITIAL_QUERY_ATTEMPTS)
                .build();
        userExchangeQueryRepository.persist(userQuery);
        log.info("New user query created for DNI {}", dni);
        return userQuery;
    }

    private void checkQueryAttempts(UserExchangeQuery userQuery, String dni) {
        if (userQuery.getQueryAttempts() >= MAX_QUERY_ATTEMPTS_PER_USER) {
            log.error("Exchange Rate Query limit exceeded for user with DNI {}", dni);
            throw new QueryLimitExceededException("Exchange Rate Query limit exceeded for user with DNI " + dni);
        }
    }

    private ExchangeRateQuery fetchAndMapExchangeRate(UserExchangeQuery userQuery) {
        ExchangeRate exchangeRate = exchangeRateApi.getExchangeRate();
        userQuery.incrementQueryAttempts();
        return ExchangeRateMapper.INSTANCE.toExchangeRateQuery(exchangeRate);
    }
}
