package com.farshad.money.controller;

import com.farshad.money.controller.converter.DtoUseCaseConverter;
import com.farshad.money.controller.dto.BaseResponseDto;
import com.farshad.money.controller.dto.CreateAccountRequestDto;
import com.farshad.money.ports.usecase.CommandUseCase;
import com.farshad.money.ports.usecase.exception.Messages;
import com.farshad.money.ports.usecase.exception.UseCaseException;
import com.farshad.money.ports.usecase.request.CreateAccountRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)

public class CreateAccountControllerTest {
    @Mock
    CommandUseCase<CreateAccountRequest> createAccountCommandUseCase;
    @Mock
    DtoUseCaseConverter<CreateAccountRequestDto,CreateAccountRequest> createAccountDtoUseCaseConverter;
    @InjectMocks
    CreateAccountController createAccountController;


    @Test
    public void createAccount_validRequest_shouldReturnResponseWithoutErrorMessage() {
        CreateAccountRequestDto createAccountRequestDto=  new CreateAccountRequestDto("13234","Josef","10");
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("13234","Josef",new BigDecimal("10"));
        when(createAccountDtoUseCaseConverter.convert(createAccountRequestDto)).thenReturn(createAccountRequest);
        //when
        BaseResponseDto baseResponseDto = createAccountController.createAccount(createAccountRequestDto);
        //then
        assertNull(baseResponseDto.getErrorMessage());
        verify(createAccountDtoUseCaseConverter).convert(createAccountRequestDto);
        verify(createAccountCommandUseCase).execute(createAccountRequest);

    }

    @Test
    public void createAccount_emptyAccountNumber_shouldReturnResponseWithErrorMessage() {
        CreateAccountRequestDto createAccountRequestDto=  new CreateAccountRequestDto("","Josef","10");
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("","Josef",new BigDecimal("10"));
        when(createAccountDtoUseCaseConverter.convert(createAccountRequestDto)).thenReturn(createAccountRequest);
        doThrow(new UseCaseException(Messages.ACCOUNT_NUMBER_IS_EMPTY)).when(createAccountCommandUseCase).execute(createAccountRequest);
        //when
        BaseResponseDto baseResponseDto = createAccountController.createAccount(createAccountRequestDto);
        //then
        assertEquals(Messages.ACCOUNT_NUMBER_IS_EMPTY,baseResponseDto.getErrorMessage());
        verify(createAccountDtoUseCaseConverter).convert(createAccountRequestDto);
        verify(createAccountCommandUseCase).execute(createAccountRequest);

    }
}