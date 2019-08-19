package com.farshad.money.adapters.database;

import com.farshad.money.adapters.database.model.JPAAccount;
import com.farshad.money.app.entity.Account;
import com.farshad.money.app.entity.Account.AccountFactory;

public class JPAAccountFactory implements AccountFactory {
    @Override
    public Account account() {
        return new JPAAccount();
    }
}
