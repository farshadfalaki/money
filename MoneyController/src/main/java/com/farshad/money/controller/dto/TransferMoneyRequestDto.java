package com.farshad.money.controller.dto;

public class TransferMoneyRequestDto {
    private String sender;
    private String receiver;
    private String amount;

    public TransferMoneyRequestDto(String sender, String receiver, String amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public TransferMoneyRequestDto() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
