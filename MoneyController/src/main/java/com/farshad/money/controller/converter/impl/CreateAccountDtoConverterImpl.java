package com.farshad.money.controller.converter.impl;

import com.farshad.money.controller.converter.DtoUseCaseConverter;
import com.farshad.money.controller.dto.CreateAccountRequestDto;
import com.farshad.money.ports.usecase.request.CreateAccountRequest;

import java.math.BigDecimal;

public class CreateAccountDtoConverterImpl implements DtoUseCaseConverter<CreateAccountRequestDto, CreateAccountRequest> {
    @Override
    public CreateAccountRequest convert(CreateAccountRequestDto createAccountRequestDto) {
        return new CreateAccountRequest(createAccountRequestDto.getAccountNumber(),createAccountRequestDto.getName(),new BigDecimal(createAccountRequestDto.getBalance()));
    }
}
