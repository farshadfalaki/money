package com.farshad.money.ports.usecase.exception;

public class NoSuchAccountNumberException extends UseCaseException{
    public NoSuchAccountNumberException(String params) {
        super(Messages.NON_EXISTING_ACCOUNT_NUMBER + params);
    }
}
