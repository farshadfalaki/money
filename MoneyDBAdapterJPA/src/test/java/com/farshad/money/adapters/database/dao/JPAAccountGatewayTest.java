package com.farshad.money.adapters.database.dao;

import com.farshad.money.adapters.database.model.JPAAccount;
import com.farshad.money.app.entity.Account;
import com.farshad.money.ports.usecase.exception.UseCaseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.persistence.NoResultException;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JPAAccountGatewayTest {
    @Mock
    JPAAccountDao jpaAccountDao;
    @InjectMocks
    JPAAccountGateway jpaAccountGateway;

    @Test(expected = UseCaseException.class)
    public void findAccountByAccountNumber_withNonExistingAccountNumber_shouldThrowUseCaseException() {
        //given
        String nonExistingAccountNumber = "12345";
        when(jpaAccountDao.findAccountByAccountNumber(nonExistingAccountNumber)).thenThrow(new NoResultException());
        //when
        jpaAccountGateway.findAccountByAccountNumber(nonExistingAccountNumber);
    }

    @Test
    public void findAccountByAccountNumber_withExistingAccountNumber_shouldReturnAccount() {
        //given
        String existingAccountNumber = "12345";
        JPAAccount jpaAccount = new JPAAccount();
        jpaAccount.setId(1200);
        jpaAccount.setAccountNumber(existingAccountNumber);
        jpaAccount.setName("Fred");
        jpaAccount.setBalance(new BigDecimal("1200"));

        when(jpaAccountDao.findAccountByAccountNumber(existingAccountNumber)).thenReturn(jpaAccount);
        //when
        Account actualAccount = jpaAccountGateway.findAccountByAccountNumber(existingAccountNumber);
        //then
        assertEquals(jpaAccount.getId(),actualAccount.getId());
        assertEquals(jpaAccount.getName(),actualAccount.getName());
        assertEquals(jpaAccount.getAccountNumber(),actualAccount.getAccountNumber());
        assertEquals(jpaAccount.getBalance(),actualAccount.getBalance());
    }
}