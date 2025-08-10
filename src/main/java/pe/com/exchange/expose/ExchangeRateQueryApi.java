package pe.com.exchange.expose;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import pe.com.exchange.dto.ExchangeRateQuery;
import pe.com.exchange.dto.UserExchangeQueryAttempts;

import java.util.List;

@Path("/exchange-rate")
public interface ExchangeRateQueryApi {
    @GET
    @Path("/attempts")
    List<UserExchangeQueryAttempts> getAllUserExchangeQueries();

    @GET
    @Path("/dni/{dni}")
    ExchangeRateQuery getExchangeRateQuery(String dni);

    @GET
    @Path("/dni/{dni}/attempts")
    UserExchangeQueryAttempts getUserExchangeQueryAttempts(String dni);
}
