package com.farshad.money.ports.usecase.request;

import java.math.BigDecimal;

public class CreateAccountRequest {

    private String accountNumber;
    private String name;
    private BigDecimal balance;

    public CreateAccountRequest() {
    }

    public CreateAccountRequest(String accountNumber, String name, BigDecimal balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CreateAccountRequest{" +
                "accountNumber='" + accountNumber + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }
}
