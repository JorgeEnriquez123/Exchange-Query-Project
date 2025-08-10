package pe.com.exchange.proxy;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import pe.com.exchange.proxy.model.ExchangeRate;

@RegisterRestClient(configKey = "exchange-rate-proxy")
@ApplicationScoped
public interface ExchangeRateProxy {
    @GET
    ExchangeRate getExchangeRate();
}
