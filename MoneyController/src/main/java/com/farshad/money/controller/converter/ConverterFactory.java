package com.farshad.money.controller.converter;

import com.farshad.money.controller.dto.CreateAccountRequestDto;
import com.farshad.money.controller.dto.TransferMoneyRequestDto;
import com.farshad.money.ports.usecase.request.CreateAccountRequest;
import com.farshad.money.ports.usecase.request.TransferMoneyRequest;

public interface ConverterFactory {
    DtoUseCaseConverter<TransferMoneyRequestDto, TransferMoneyRequest>   transferMoneyDtoConverter();
    DtoUseCaseConverter<CreateAccountRequestDto, CreateAccountRequest>   createAccountDtoConverter();
}
