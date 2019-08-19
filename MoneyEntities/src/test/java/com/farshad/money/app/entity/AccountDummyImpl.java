package com.farshad.money.app.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class AccountDummyImpl extends Account{

    private long id;
    private String accountNumber;
    private String name;
    private BigDecimal balance;

    AccountDummyImpl(long id, String accountNumber, String name, BigDecimal balance) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDummyImpl that = (AccountDummyImpl) o;
        return id == that.id &&
                Objects.equals(accountNumber, that.accountNumber) &&
                Objects.equals(name, that.name) &&
                Objects.equals(balance, that.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, name, balance);
    }
}
