package pe.com.exchange.utils.exceptions.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import pe.com.exchange.utils.exceptions.ExchangeRateFetchException;

@Provider
public class ExchangeRateFetchExceptionHandler implements ExceptionMapper<ExchangeRateFetchException> {
    @Override
    public Response toResponse(ExchangeRateFetchException exception) {
        return Response
                .status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(ErrorMessage.builder()
                        .code(Response.Status.SERVICE_UNAVAILABLE.getStatusCode())
                        .message(exception.getMessage())
                        .build())
                .build();
    }
}
