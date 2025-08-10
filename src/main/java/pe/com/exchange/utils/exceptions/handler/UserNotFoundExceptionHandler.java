package pe.com.exchange.utils.exceptions.handler;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import pe.com.exchange.utils.exceptions.UserNotFoundException;

@Provider
public class UserNotFoundExceptionHandler implements ExceptionMapper<UserNotFoundException> {
    @Override
    public Response toResponse(UserNotFoundException exception) {
        return Response
                .status(Response.Status.CONFLICT)
                .entity(ErrorMessage.builder()
                        .code(Response.Status.CONFLICT.getStatusCode())
                        .message(exception.getMessage())
                        .build())
                .build();
    }
}
