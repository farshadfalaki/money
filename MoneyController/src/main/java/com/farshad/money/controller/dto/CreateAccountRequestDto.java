package com.farshad.money.controller.dto;


public class CreateAccountRequestDto {
    private String accountNumber;
    private String name;
    private String balance;

    public CreateAccountRequestDto(String accountNumber, String name, String balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public CreateAccountRequestDto() {
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
}
