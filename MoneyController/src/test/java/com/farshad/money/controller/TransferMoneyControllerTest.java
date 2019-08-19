package com.farshad.money.controller;

import com.farshad.money.controller.converter.DtoUseCaseConverter;
import com.farshad.money.controller.dto.BaseResponseDto;
import com.farshad.money.controller.dto.TransferMoneyRequestDto;
import com.farshad.money.ports.usecase.CommandUseCase;
import com.farshad.money.ports.usecase.exception.Messages;
import com.farshad.money.ports.usecase.exception.UseCaseException;
import com.farshad.money.ports.usecase.request.TransferMoneyRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransferMoneyControllerTest {
    @Mock
    CommandUseCase<TransferMoneyRequest> transferMoneyCommandUseCase;
    @Mock
    DtoUseCaseConverter<TransferMoneyRequestDto,TransferMoneyRequest> transferMoneyDtoUseCaseConverter;
    @InjectMocks
    TransferMoneyController transferMoneyController;

    @Test
    public void transferMoney_validRequest_shouldReturnResponseWithoutErrorMessage() {
        //given
        TransferMoneyRequestDto transferMoneyRequestDto = new TransferMoneyRequestDto("1010","2020","1500");
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("1010","2020",new BigDecimal("1500"));
        when(transferMoneyDtoUseCaseConverter.convert(transferMoneyRequestDto)).thenReturn(transferMoneyRequest);
        //when
        BaseResponseDto baseResponseDto = transferMoneyController.transferMoney(transferMoneyRequestDto);
        //then
        assertNull(baseResponseDto.getErrorMessage());
        verify(transferMoneyDtoUseCaseConverter).convert(transferMoneyRequestDto);
        verify(transferMoneyCommandUseCase).execute(transferMoneyRequest);
    }

    @Test
    public void transferMoney_emptyDestinationAccount_shouldReturnResponseWithErrorMessage() {
        //given
        TransferMoneyRequestDto transferMoneyRequestDto = new TransferMoneyRequestDto("1010","","1500");
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("1010","",new BigDecimal("1500"));
        when(transferMoneyDtoUseCaseConverter.convert(transferMoneyRequestDto)).thenReturn(transferMoneyRequest);
        doThrow(new UseCaseException(Messages.ACCOUNT_NUMBER_IS_EMPTY)).when(transferMoneyCommandUseCase).execute(transferMoneyRequest);
        //when
        BaseResponseDto baseResponseDto = transferMoneyController.transferMoney(transferMoneyRequestDto);
        //then
        assertEquals(Messages.ACCOUNT_NUMBER_IS_EMPTY,baseResponseDto.getErrorMessage());
        verify(transferMoneyDtoUseCaseConverter).convert(transferMoneyRequestDto);
        verify(transferMoneyCommandUseCase).execute(transferMoneyRequest);
    }
}