package com.farshad.money.controller;

import com.farshad.money.controller.converter.DtoUseCaseConverter;
import com.farshad.money.controller.dto.BaseResponseDto;
import com.farshad.money.controller.dto.CreateAccountRequestDto;
import com.farshad.money.ports.usecase.CommandUseCase;
import com.farshad.money.ports.usecase.exception.UseCaseException;
import com.farshad.money.ports.usecase.request.CreateAccountRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


public class CreateAccountController {
    //Logger logger = LoggerFactory.getLogger(CreateAccountController.class);
    private CommandUseCase<CreateAccountRequest> createAccountCommandUseCase;
    private DtoUseCaseConverter<CreateAccountRequestDto,CreateAccountRequest> createAccountDtoUseCaseConverter;

    CreateAccountController(CommandUseCase<CreateAccountRequest> createAccountCommandUseCase, DtoUseCaseConverter<CreateAccountRequestDto, CreateAccountRequest> createAccountDtoUseCaseConverter) {
        this.createAccountCommandUseCase = createAccountCommandUseCase;
        this.createAccountDtoUseCaseConverter = createAccountDtoUseCaseConverter;
    }

    public BaseResponseDto createAccount(CreateAccountRequestDto createAccountRequestDto){
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            createAccountCommandUseCase.execute(createAccountDtoUseCaseConverter.convert(createAccountRequestDto));
        }catch (UseCaseException e){
            baseResponseDto.setErrorMessage(e.getMessage());
        }
        return baseResponseDto;
    }
}
