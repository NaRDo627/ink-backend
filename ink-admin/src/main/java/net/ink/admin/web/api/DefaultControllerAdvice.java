package net.ink.admin.web.api;

import net.ink.core.core.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Priority;

@Priority(10)
@RestControllerAdvice
public class DefaultControllerAdvice extends AbstractControllerAdvice {
    @ExceptionHandler(value = { Exception.class, InkException.class })
    public ResponseEntity<ApiResult<?>> handleUnknownException(Exception e) {
        return handleException(e, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { BadRequestException.class })
    public ResponseEntity<ApiResult<?>> handleBadRequestException(Exception e) {
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ResourceNotFoundException.class})
    public ResponseEntity<ApiResult<?>> handleNotFoundException(Exception e) {
        return handleException(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { AccessNotAllowedException.class})
    public ResponseEntity<ApiResult<?>> handleForbiddenException(Exception e) {
        return handleException(e, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = { UnauthorizedException.class})
    public ResponseEntity<ApiResult<?>> handleUnauthorizedException(Exception e) {
        return handleException(e, HttpStatus.UNAUTHORIZED);
    }

}
