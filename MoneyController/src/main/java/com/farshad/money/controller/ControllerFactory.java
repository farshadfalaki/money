package com.farshad.money.controller;

public interface ControllerFactory {
    CreateAccountController createAccountController();
    TransferMoneyController transferMoneyController();
}
