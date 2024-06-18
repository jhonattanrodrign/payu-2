package pagos.payu.application.exceptions;

/**
 * Invalid Authorization Request Exception Handler
 */
public class InvalidAuthorizationRequestException extends RuntimeException {
    public InvalidAuthorizationRequestException(String message) {
        super(message);
    }
}