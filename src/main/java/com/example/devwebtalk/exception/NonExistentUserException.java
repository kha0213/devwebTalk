package com.example.devwebtalk.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.util.NoSuchElementException;
public class NonExistentUserException extends NoSuchElementException {
    public NonExistentUserException() {
        super();
    }
    public NonExistentUserException(String errorMessage) {
        super(errorMessage);
    }
}
