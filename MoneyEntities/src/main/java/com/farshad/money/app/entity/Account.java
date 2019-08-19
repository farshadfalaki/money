package com.farshad.money.app.entity;

import java.math.BigDecimal;

public abstract class Account {
    public abstract long getId();
    public abstract void setId(long id);

    public abstract String getAccountNumber();
    public abstract void setAccountNumber(String accountNumber);

    public abstract String getName();
    public abstract void setName(String name);

    public abstract BigDecimal getBalance();
    public abstract void setBalance(BigDecimal balance);

    public static interface AccountFactory {
        Account account();
    }

    public final void increaseBalance(BigDecimal amount){
        this.setBalance(this.getBalance().add(amount));
    }
    public final void decreaseBalance(BigDecimal amount){
        this.setBalance(this.getBalance().subtract(amount));
    }
}
