package net.ink.admin.web.api.advice;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public abstract class AbstractControllerAdvice {
    protected ResponseEntity<ApiResult<?>> handleException(Throwable e, HttpStatus status) {
        log.error(e.getMessage(), e);
        return handleException(e, status, status.value());
    }

    protected ResponseEntity<ApiResult<?>> handleException(Throwable e, HttpStatus status, int errorCode) {
        return ResponseEntity.status(status)
                .body(ApiResult.fail(e.getMessage()));
    }

    @Getter
    public static class ApiResult<T> {
        protected final String message;
        protected final T data;
        protected final long timestamp;

        public ApiResult(String message, T data) {
            this.message = message;
            this.data = data;
            this.timestamp = System.currentTimeMillis();
        }

        public static <T> ApiResult<T> ok(T data) {
            return ok(data, "ok");
        }

        public static <T> ApiResult<T> ok(T data, String message) {
            return new ApiResult<>(message, data);
        }

        public static ApiResult<?> fail(String message) {
            return new ApiResult<>(message, null);
        }
    }

}
