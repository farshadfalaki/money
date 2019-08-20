package com.farshad.money.ports.usecase.exception;

public class EmptyDestinationAccountNumberException extends UseCaseException{
    public EmptyDestinationAccountNumberException() {
        super(Messages.DEST_ACCOUNT_NUMBER_IS_EMPTY);
    }
}
