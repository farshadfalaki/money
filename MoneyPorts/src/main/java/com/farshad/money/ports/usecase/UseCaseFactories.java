package com.farshad.money.ports.usecase;

import com.farshad.money.ports.usecase.request.CreateAccountRequest;
import com.farshad.money.ports.usecase.request.TransferMoneyRequest;

public interface UseCaseFactories {
    CommandUseCase<CreateAccountRequest> createAccountUseCase();
    CommandUseCase<TransferMoneyRequest> transferMoneyUseCase();
}
