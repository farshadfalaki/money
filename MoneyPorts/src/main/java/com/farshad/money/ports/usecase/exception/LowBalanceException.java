package com.farshad.money.ports.usecase.exception;

public class LowBalanceException extends UseCaseException{
    public LowBalanceException(String params) {
        super(Messages.BALANCE_IS_LOW + params);
    }
}
