package com.farshad.money.ports.usecase.exception;

public class NegativeAmountException extends UseCaseException{
    public NegativeAmountException() {
        super(Messages.AMOUNT_IS_NEGATIVE);
    }
}
