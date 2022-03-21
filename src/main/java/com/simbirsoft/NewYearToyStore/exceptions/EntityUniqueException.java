package com.simbirsoft.NewYearToyStore.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class EntityUniqueException extends RuntimeException{

    HttpStatus httpStatus;
    List<String> errors;
    Object data;

    public EntityUniqueException(String message) {
        this(HttpStatus.UNPROCESSABLE_ENTITY, message);
    }

    public EntityUniqueException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public EntityUniqueException(HttpStatus httpStatus, String message) {
        this(httpStatus, message, Collections.singletonList(message), null);
    }

    public EntityUniqueException(HttpStatus httpStatus, String message, Object data) {
        this(httpStatus, message, Collections.singletonList(message), data);
    }

    public EntityUniqueException(HttpStatus httpStatus, String message, List<String> errors) {
        this(httpStatus, message, errors, null);
    }

    public EntityUniqueException(HttpStatus httpStatus, String message, List<String> errors, Object data) {
        super(message);
        this.httpStatus = httpStatus;
        this.errors = errors;
        this.data = data;
    }


}
