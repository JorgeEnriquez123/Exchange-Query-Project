package pe.com.exchange.expose;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
import pe.com.exchange.dto.ExchangeRateQuery;
import pe.com.exchange.dto.UserExchangeQueryAttempts;
import pe.com.exchange.service.UserExchangeQueryService;

import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor
public class ExchangeRateQueryApiImpl implements ExchangeRateQueryApi{
    private final UserExchangeQueryService userExchangeQueryService;

    @Override
    public List<UserExchangeQueryAttempts> getAllUserExchangeQueries() {
        return userExchangeQueryService.getAllUserExchangeQueries();
    }

    @Override
    public ExchangeRateQuery getExchangeRateQuery(String dni) {
        return userExchangeQueryService.getExchangeRateQuery(dni);
    }

    @Override
    public UserExchangeQueryAttempts getUserExchangeQueryAttempts(String dni) {
        return userExchangeQueryService.getUserExchangeQueryAttempts(dni);
    }
}
