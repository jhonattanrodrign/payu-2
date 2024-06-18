package pagos.payu.application.exceptions;

public class InvalidAuthorizationRequestException extends RuntimeException {
    public InvalidAuthorizationRequestException(String message) {
        super(message);
    }
}