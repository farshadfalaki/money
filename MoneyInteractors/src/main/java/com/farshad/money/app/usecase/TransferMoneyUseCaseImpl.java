package com.farshad.money.app.usecase;

import com.farshad.money.app.entity.Account;
import com.farshad.money.ports.persistence.AccountGateway;
import com.farshad.money.ports.persistence.TransactionalRunner;
import com.farshad.money.ports.usecase.exception.Messages;
import com.farshad.money.ports.usecase.exception.UseCaseException;
import com.farshad.money.ports.usecase.request.TransferMoneyRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class TransferMoneyUseCaseImpl extends TransactionalCommandUseCase<TransferMoneyRequest> {
    private Logger logger = LoggerFactory.getLogger(TransferMoneyUseCaseImpl.class);

    private AccountGateway accountGateway;

    public TransferMoneyUseCaseImpl(TransactionalRunner transactionalRunner,AccountGateway accountGateway) {
        super(transactionalRunner);
        this.accountGateway = accountGateway;
    }


    @Override
    public void executeInTransaction(TransferMoneyRequest transferMoneyRequest) {
        logger.info(">>executeInTransaction " + transferMoneyRequest);
        validateRequestData(transferMoneyRequest);
        Account account = accountGateway.findAccountByAccountNumber(transferMoneyRequest.getAccountNumber());
        Account destAccount = accountGateway.findAccountByAccountNumber(transferMoneyRequest.getDestAccountNumber());
        validateBusinessRule(account,transferMoneyRequest.getAmount());
        account.decreaseBalance(transferMoneyRequest.getAmount());
        destAccount.increaseBalance(transferMoneyRequest.getAmount());
        accountGateway.updateAccount(account);
        accountGateway.updateAccount(destAccount);
        logger.info("<<executeInTransaction ");
    }

    void validateRequestData(TransferMoneyRequest transferMoneyRequest){
        if(transferMoneyRequest.getAccountNumber() == null || "".equals(transferMoneyRequest.getAccountNumber())){
            throw new UseCaseException(Messages.ACCOUNT_NUMBER_IS_EMPTY);
        }

        if(transferMoneyRequest.getDestAccountNumber() == null || "".equals(transferMoneyRequest.getDestAccountNumber())){
            throw new UseCaseException(Messages.DEST_ACCOUNT_NUMBER_IS_EMPTY);
        }

        if( (transferMoneyRequest.getAmount() == null) || (transferMoneyRequest.getAmount().compareTo(new BigDecimal("0")) <= 0 )){
            throw new UseCaseException(Messages.AMOUNT_IS_NEGATIVE);
        }

    }
    void validateBusinessRule(Account account, BigDecimal amount){
        if(account.getBalance().compareTo(amount) <= 0){
            throw new UseCaseException(Messages.BALANCE_IS_LOW + " [" + account.getAccountNumber()+ ","  + amount +"]");
        }
    }

}
