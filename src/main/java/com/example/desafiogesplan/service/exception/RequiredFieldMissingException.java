package com.example.desafiogesplan.service.exception;

public class RequiredFieldMissingException extends RuntimeException{

        public RequiredFieldMissingException(String message) {
            super(message);
        }
}
