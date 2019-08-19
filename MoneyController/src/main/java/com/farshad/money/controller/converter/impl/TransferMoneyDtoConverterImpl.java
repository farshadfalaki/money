package com.farshad.money.controller.converter.impl;

import com.farshad.money.controller.converter.DtoUseCaseConverter;
import com.farshad.money.controller.dto.TransferMoneyRequestDto;
import com.farshad.money.ports.usecase.request.TransferMoneyRequest;

import java.math.BigDecimal;

public class TransferMoneyDtoConverterImpl implements DtoUseCaseConverter<TransferMoneyRequestDto,TransferMoneyRequest> {
    @Override
    public TransferMoneyRequest convert(TransferMoneyRequestDto transferMoneyRequestDto) {
        return new TransferMoneyRequest(transferMoneyRequestDto.getSender(), transferMoneyRequestDto.getReceiver(),new BigDecimal(transferMoneyRequestDto.getAmount()));
    }
}
