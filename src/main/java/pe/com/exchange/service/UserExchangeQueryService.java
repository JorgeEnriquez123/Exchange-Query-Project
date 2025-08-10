package pe.com.exchange.service;

import pe.com.exchange.dto.ExchangeRateQuery;
import pe.com.exchange.dto.UserExchangeQueryAttempts;

import java.util.List;

public interface UserExchangeQueryService {
    List<UserExchangeQueryAttempts> getAllUserExchangeQueries();
    ExchangeRateQuery getExchangeRateQuery(String dni);
    UserExchangeQueryAttempts getUserExchangeQueryAttempts(String dni);
}
