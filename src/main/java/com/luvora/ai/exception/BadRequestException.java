package com.luvora.ai.exception;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE,  makeFinal = true)
public class BadRequestException extends RuntimeException{
    String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }
}
