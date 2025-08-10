package pe.com.exchange.utils.exceptions;

public class QueryLimitExceededException extends RuntimeException {
    public QueryLimitExceededException(String message) {
        super(message);
    }
}
