package com.farshad.money.controller.converter.impl;

import com.farshad.money.controller.dto.CreateAccountRequestDto;
import com.farshad.money.ports.usecase.request.CreateAccountRequest;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CreateAccountDtoConverterImplTest {

    CreateAccountDtoConverterImpl createAccountDtoConverter;

    @Before
    public void init(){
        createAccountDtoConverter = new CreateAccountDtoConverterImpl();
    }
    @Test
    public void convert() {
        //given
        CreateAccountRequestDto createAccountRequestDto = new CreateAccountRequestDto("24567","David","450000");
        //when
        CreateAccountRequest createAccountRequest = createAccountDtoConverter.convert(createAccountRequestDto);
        //then
        assertEquals(createAccountRequestDto.getAccountNumber(),createAccountRequest.getAccountNumber());
        assertEquals(createAccountRequestDto.getName(),createAccountRequest.getName());
        assertEquals(new BigDecimal(createAccountRequestDto.getBalance()),createAccountRequest.getBalance());
    }
}