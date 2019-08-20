package com.farshad.money.ports.persistence;

import com.farshad.money.app.entity.Account;

public interface AccountGateway {
    Account findAccountByAccountNumber(String  accountNumber);
    boolean accountNumberExists(String  accountNumber);
    void updateAccount(Account account);
    void createAccount(Account account);
}
