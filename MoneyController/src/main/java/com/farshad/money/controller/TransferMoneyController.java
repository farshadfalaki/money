package com.farshad.money.controller;

import com.farshad.money.controller.converter.DtoUseCaseConverter;
import com.farshad.money.controller.dto.BaseResponseDto;
import com.farshad.money.controller.dto.TransferMoneyRequestDto;
import com.farshad.money.ports.usecase.CommandUseCase;
import com.farshad.money.ports.usecase.exception.UseCaseException;
import com.farshad.money.ports.usecase.request.TransferMoneyRequest;

public class TransferMoneyController {
    private CommandUseCase<TransferMoneyRequest> transferMoneyCommandUseCase;
    private DtoUseCaseConverter<TransferMoneyRequestDto,TransferMoneyRequest> transferMoneyDtoUseCaseConverter;

    TransferMoneyController(CommandUseCase<TransferMoneyRequest> transferMoneyCommandUseCase, DtoUseCaseConverter<TransferMoneyRequestDto, TransferMoneyRequest> transferMoneyDtoUseCaseConverter) {
        this.transferMoneyCommandUseCase = transferMoneyCommandUseCase;
        this.transferMoneyDtoUseCaseConverter = transferMoneyDtoUseCaseConverter;
    }

    public BaseResponseDto transferMoney(TransferMoneyRequestDto transferMoneyRequestDto){
        BaseResponseDto baseResponseDto = new BaseResponseDto();
        try {
            transferMoneyCommandUseCase.execute(transferMoneyDtoUseCaseConverter.convert(transferMoneyRequestDto));
        }catch (UseCaseException e){
            baseResponseDto.setErrorMessage(e.getMessage());
        }
        return baseResponseDto;
    }
}
