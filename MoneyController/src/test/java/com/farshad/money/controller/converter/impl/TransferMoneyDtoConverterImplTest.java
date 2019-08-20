package com.farshad.money.controller.converter.impl;

import com.farshad.money.controller.dto.TransferMoneyRequestDto;
import com.farshad.money.ports.usecase.request.TransferMoneyRequest;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class TransferMoneyDtoConverterImplTest {

    TransferMoneyDtoConverterImpl transferMoneyDtoConverter;

    @Before
    public void init(){
        transferMoneyDtoConverter = new TransferMoneyDtoConverterImpl();
    }
    @Test
    public void convert() {
        //given
        TransferMoneyRequestDto transferMoneyRequestDto = new TransferMoneyRequestDto("13456","5432","340000");
        //when
        TransferMoneyRequest transferMoneyRequest = transferMoneyDtoConverter.convert(transferMoneyRequestDto);
        //then
        assertEquals(transferMoneyRequestDto.getSender(),transferMoneyRequest.getAccountNumber());
        assertEquals(transferMoneyRequestDto.getReceiver(),transferMoneyRequest.getDestAccountNumber());
        assertEquals(new BigDecimal(transferMoneyRequestDto.getAmount()),transferMoneyRequest.getAmount());
    }
}