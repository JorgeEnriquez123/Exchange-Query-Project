package pe.com.exchange.proxy;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import pe.com.exchange.proxy.model.ExchangeRate;
import pe.com.exchange.utils.exceptions.ExchangeRateFetchException;

@Slf4j
@ApplicationScoped
public class ExchangeRateApi {
    private final ExchangeRateProxy exchangeRateProxy;

    public ExchangeRateApi(@RestClient ExchangeRateProxy exchangeRateProxy) {
        this.exchangeRateProxy = exchangeRateProxy;
    }

    public ExchangeRate getExchangeRate() {
        try {
            return exchangeRateProxy.getExchangeRate();
        } catch (Exception e) {
            log.error("Error al obtener la tasa de cambio", e);
            throw new ExchangeRateFetchException("Error al obtener la tasa de cambio", e);
        }
    }
}
