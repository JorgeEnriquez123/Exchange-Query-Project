package pe.com.exchange.utils.exceptions.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import pe.com.exchange.utils.exceptions.InvalidDniException;

@Provider
public class InvalidDniExceptionHandler implements ExceptionMapper<InvalidDniException> {
    @Override
    public Response toResponse(InvalidDniException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(ErrorMessage.builder()
                        .code(Response.Status.BAD_REQUEST.getStatusCode())
                        .message(exception.getMessage())
                        .build())
                .build();
    }
}
