package com.Exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "NO ITEM FOUND")
public class NoItemFoundException extends RuntimeException {


    public NoItemFoundException(String message) {
        super(message);
    }


}
