package com.farshad.money.app.entity;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void increaseBalance_addSomeAmount_shouldJustIncreaseBalanceWithSameAmountNothingElse() {
        //given
        Account actualAccount = new AccountDummyImpl(12,"12345","alexander",new BigDecimal("100"));
        Account expectedAccount = new AccountDummyImpl(12,"12345","alexander",new BigDecimal("110"));
        //when
        actualAccount.increaseBalance(new BigDecimal("10"));
        //then
        assertEquals(expectedAccount,actualAccount);
    }

    @Test
    public void decreaseBalance_subtractSomeAmount_shouldJustDecreaseBalanceWithSameAmountNothingElse() {
        //given
        Account actualAccount = new AccountDummyImpl(12,"12345","alexander",new BigDecimal("100"));
        Account expectedAccount = new AccountDummyImpl(12,"12345","alexander",new BigDecimal("90"));
        //when
        actualAccount.decreaseBalance(new BigDecimal("10"));
        //then
        assertEquals(expectedAccount,actualAccount);
    }
}