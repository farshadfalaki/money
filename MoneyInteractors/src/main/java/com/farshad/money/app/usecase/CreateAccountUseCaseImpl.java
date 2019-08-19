package com.farshad.money.app.usecase;

import com.farshad.money.app.entity.Account;
import com.farshad.money.app.entity.Account.AccountFactory;
import com.farshad.money.ports.database.AccountGateway;
import com.farshad.money.ports.database.TransactionalRunner;
import com.farshad.money.ports.usecase.exception.Messages;
import com.farshad.money.ports.usecase.exception.UseCaseException;
import com.farshad.money.ports.usecase.request.CreateAccountRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class CreateAccountUseCaseImpl extends TransactionalCommandUseCase<CreateAccountRequest> {
    Logger logger = LoggerFactory.getLogger(CreateAccountUseCaseImpl.class);

    private AccountFactory accountFactory;
    private AccountGateway accountGateway;

    public CreateAccountUseCaseImpl(TransactionalRunner transactionalRunner, AccountFactory accountFactory, AccountGateway accountGateway) {
        super(transactionalRunner);
        this.accountFactory = accountFactory;
        this.accountGateway = accountGateway;
    }


    @Override
    protected void executeInTransaction(CreateAccountRequest createAccountRequest) {
        logger.info(">>executeInTransaction " + createAccountRequest);
        validateRequestData(createAccountRequest);
        validateBusinessRule(createAccountRequest);
        Account account = accountFactory.account();
        setFields(account,createAccountRequest);
        accountGateway.createAccount(account);
    }


    void validateRequestData(CreateAccountRequest createAccountRequest) {
        if(createAccountRequest.getAccountNumber() == null || "".equals(createAccountRequest.getAccountNumber())){
            throw new UseCaseException(Messages.ACCOUNT_NUMBER_IS_EMPTY);
        }

        if(createAccountRequest.getName() == null || "".equals(createAccountRequest.getName())){
            throw new UseCaseException(Messages.NAME_IS_EMPTY);
        }

        if( (createAccountRequest.getBalance() == null) || (createAccountRequest.getBalance().compareTo(new BigDecimal("0")) < 0 )){
            throw new UseCaseException(Messages.AMOUNT_IS_NEGATIVE);
        }
    }

    void setFields(Account account, CreateAccountRequest createAccountRequest) {
        account.setAccountNumber(createAccountRequest.getAccountNumber());
        account.setName(createAccountRequest.getName());
        account.setBalance(createAccountRequest.getBalance());
    }
    void validateBusinessRule(CreateAccountRequest createAccountRequest){
        if(accountGateway.accountNumberExists(createAccountRequest.getAccountNumber())){
            throw new UseCaseException(Messages.ACCOUNT_NUMBER_EXISTS);
        }
    }
}
