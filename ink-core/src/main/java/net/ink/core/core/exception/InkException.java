package net.ink.core.core.exception;

/**
 * 잉크 도메인 비즈니스 예외 (500 Server Error)
 */
public class InkException extends RuntimeException {

    public InkException() {
    }

    public InkException(String message) {
        super(message);
    }

    public InkException(String message, Throwable cause) {
        super(message, cause);
    }

    public InkException(Throwable cause) {
        super(cause);
    }

    public InkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
