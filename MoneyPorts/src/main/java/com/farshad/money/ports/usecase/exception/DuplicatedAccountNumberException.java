package com.farshad.money.ports.usecase.exception;

public class DuplicatedAccountNumberException extends UseCaseException{
    public DuplicatedAccountNumberException() {
        super(Messages.ACCOUNT_NUMBER_EXISTS);
    }
}
