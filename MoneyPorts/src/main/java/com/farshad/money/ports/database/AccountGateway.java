package com.farshad.money.ports.database;

import com.farshad.money.app.entity.Account;

public interface AccountGateway {
    Account findAccountByAccountNumber(String  accountNumber);
    void updateAccount(Account account);
    void createAccount(Account account);
}
