package com.mensal.project.configuration.exception;

public class MailSendException extends RuntimeException{
    public MailSendException(String message) {
        super(message);
    }
}
