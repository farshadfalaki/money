package com.farshad.money.controller;

import com.farshad.money.controller.converter.ConverterFactory;
import com.farshad.money.ports.usecase.UseCaseFactories;

public class ControllerFactoryImpl implements ControllerFactory{
    private TransferMoneyController transferMoneyController;
    private CreateAccountController createAccountController;

    public ControllerFactoryImpl(UseCaseFactories useCaseFactories, ConverterFactory converterFactory) {
        transferMoneyController = new TransferMoneyController(useCaseFactories.transferMoneyUseCase(),converterFactory.transferMoneyDtoConverter());
        createAccountController = new CreateAccountController(useCaseFactories.createAccountUseCase(),converterFactory.createAccountDtoConverter());
    }

    @Override
    public CreateAccountController createAccountController() {
        return createAccountController;
    }

    @Override
    public TransferMoneyController transferMoneyController() {
        return transferMoneyController;
    }
}
