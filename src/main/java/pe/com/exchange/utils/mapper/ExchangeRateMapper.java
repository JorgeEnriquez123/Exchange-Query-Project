package pe.com.exchange.utils.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import pe.com.exchange.dto.ExchangeRateQuery;
import pe.com.exchange.dto.UserExchangeQueryAttempts;
import pe.com.exchange.model.entity.UserExchangeQuery;
import pe.com.exchange.proxy.model.ExchangeRate;

@Mapper
public interface ExchangeRateMapper {
    ExchangeRateMapper INSTANCE = Mappers.getMapper(ExchangeRateMapper.class);

    ExchangeRateQuery toExchangeRateQuery(ExchangeRate exchangeRate);
    UserExchangeQueryAttempts toUserExchangeQueryAttempts(UserExchangeQuery userExchangeQuery);
}
