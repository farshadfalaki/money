package com.farshad.money.ports.usecase.request;

import java.math.BigDecimal;

public class TransferMoneyRequest {

    private String accountNumber;
    private String destAccountNumber;
    private BigDecimal  amount;

    public TransferMoneyRequest() {
    }

    public TransferMoneyRequest(String accountNumber, String destAccountNumber, BigDecimal amount) {
        this.accountNumber = accountNumber;
        this.destAccountNumber = destAccountNumber;
        this.amount = amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getDestAccountNumber() {
        return destAccountNumber;
    }

    public void setDestAccountNumber(String destAccountNumber) {
        this.destAccountNumber = destAccountNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransferMoneyRequest{" +
                "accountNumber='" + accountNumber + '\'' +
                ", destAccountNumber='" + destAccountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
