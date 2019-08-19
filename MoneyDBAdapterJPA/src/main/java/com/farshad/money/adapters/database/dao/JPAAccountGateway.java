package com.farshad.money.adapters.database.dao;

import com.farshad.money.adapters.database.model.JPAAccount;
import com.farshad.money.app.entity.Account;
import com.farshad.money.ports.database.AccountGateway;
import com.farshad.money.ports.usecase.exception.Messages;
import com.farshad.money.ports.usecase.exception.UseCaseException;

import javax.persistence.NoResultException;


public class JPAAccountGateway implements AccountGateway {

    public JPAAccountGateway(JPAAccountDao jpaAccountDao) {
        this.jpaAccountDao = jpaAccountDao;
    }

    private JPAAccountDao jpaAccountDao;

    @Override
    public Account findAccountByAccountNumber(String accountNumber) {
        Account account ;
        try {
            account = jpaAccountDao.findAccountByAccountNumber(accountNumber);
        }catch (NoResultException e){
            throw new UseCaseException(Messages.NON_EXISTING_ACCOUNT_NUMBER + " [" + accountNumber + "]");
        }
        return account;
    }

    @Override
    public void updateAccount(Account account) {
        jpaAccountDao.persist((JPAAccount) account);
    }

    @Override
    public void createAccount(Account account) {
        jpaAccountDao.persist((JPAAccount) account);
    }
}
