package com.farshad.money.controller.converter.impl;

import com.farshad.money.controller.converter.ConverterFactory;
import com.farshad.money.controller.converter.DtoUseCaseConverter;
import com.farshad.money.controller.dto.CreateAccountRequestDto;
import com.farshad.money.controller.dto.TransferMoneyRequestDto;
import com.farshad.money.ports.usecase.request.CreateAccountRequest;
import com.farshad.money.ports.usecase.request.TransferMoneyRequest;

public class ConverterFactoryImpl implements ConverterFactory {

    @Override
    public DtoUseCaseConverter<TransferMoneyRequestDto, TransferMoneyRequest> transferMoneyDtoConverter() {
        return new TransferMoneyDtoConverterImpl();
    }

    @Override
    public DtoUseCaseConverter<CreateAccountRequestDto, CreateAccountRequest> createAccountDtoConverter() {
        return new CreateAccountDtoConverterImpl();
    }
}
