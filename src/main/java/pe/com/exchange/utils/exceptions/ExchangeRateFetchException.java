package pe.com.exchange.utils.exceptions;

public class ExchangeRateFetchException extends RuntimeException {
    public ExchangeRateFetchException(String message, Throwable cause) {
        super(message, cause);
    }
}
