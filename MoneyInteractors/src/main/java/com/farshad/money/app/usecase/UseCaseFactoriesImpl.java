package com.farshad.money.app.usecase;

import com.farshad.money.app.entity.Account;
import com.farshad.money.ports.persistence.AccountGateway;
import com.farshad.money.ports.persistence.TransactionalRunner;
import com.farshad.money.ports.usecase.CommandUseCase;
import com.farshad.money.ports.usecase.UseCaseFactories;
import com.farshad.money.ports.usecase.request.CreateAccountRequest;
import com.farshad.money.ports.usecase.request.TransferMoneyRequest;

public class UseCaseFactoriesImpl implements UseCaseFactories {

    private AccountGateway accountGateway;
    private TransactionalRunner transactionalRunner;
    private Account.AccountFactory accountFactory;


    public UseCaseFactoriesImpl(AccountGateway accountGateway, TransactionalRunner transactionalRunner, Account.AccountFactory accountFactory) {
        this.accountGateway = accountGateway;
        this.transactionalRunner = transactionalRunner;
        this.accountFactory = accountFactory;
    }

    @Override
    public CommandUseCase<CreateAccountRequest> createAccountUseCase() {
        return new CreateAccountUseCaseImpl(transactionalRunner,accountFactory,accountGateway);
    }

    @Override
    public CommandUseCase<TransferMoneyRequest> transferMoneyUseCase() {
        return new TransferMoneyUseCaseImpl(transactionalRunner,accountGateway);
    }
}
