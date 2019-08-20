package com.farshad.money.ports.usecase.exception;

public class EmptyNameException extends UseCaseException{
    public EmptyNameException() {
        super(Messages.NAME_IS_EMPTY);
    }
}
