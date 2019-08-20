package com.farshad.money.ports.usecase.exception;

public class EmptyAccountNumberException extends UseCaseException{
    public EmptyAccountNumberException() {
        super(Messages.ACCOUNT_NUMBER_IS_EMPTY);
    }
}
