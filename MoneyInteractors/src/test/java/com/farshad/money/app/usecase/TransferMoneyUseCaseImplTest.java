package com.farshad.money.app.usecase;

import com.farshad.money.app.entity.Account;
import com.farshad.money.ports.persistence.AccountGateway;
import com.farshad.money.ports.usecase.exception.*;
import com.farshad.money.ports.usecase.request.TransferMoneyRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TransferMoneyUseCaseImplTest {
    @Mock
    AccountGateway accountGateway;
    @Mock
    Account account;
    @Spy
    @InjectMocks
    TransferMoneyUseCaseImpl transferMoneyUseCase;

    @Test(expected = EmptyAccountNumberException.class)
    public void validateRequestData_emptyAccountNumber_shouldThrowUseCaseException() {
        //given
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("","1234",new BigDecimal("230"));
        //when
        transferMoneyUseCase.validateRequestData(transferMoneyRequest);
    }

    @Test(expected = EmptyDestinationAccountNumberException.class)
    public void validateRequestData_emptyDestinationAccountNumber_shouldThrowEmptyDestinationAccountNumberException() {
        //given
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("1234","",new BigDecimal("230"));
        //when
        transferMoneyUseCase.validateRequestData(transferMoneyRequest);
    }

    @Test(expected = NegativeAmountException.class)
    public void validateRequestData_zeroTransferAmount_shouldThrowNegativeAmountException() {
        //given
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("1234","2345",new BigDecimal("-10"));
        //when
        transferMoneyUseCase.validateRequestData(transferMoneyRequest);
    }

    @Test
    public void validateRequestData_validData_shouldNotThrowException() {
        //given
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("1234","2345",new BigDecimal("900"));
        //when
        transferMoneyUseCase.validateRequestData(transferMoneyRequest);
    }

    @Test(expected = LowBalanceException.class)
    public void validateBusinessRule_amountMoreThanBalance_shouldThrowLowBalanceException() {
        //given
        BigDecimal amount = new BigDecimal("100000");
        when(account.getBalance()).thenReturn(new BigDecimal("40000"));
        //when
        transferMoneyUseCase.validateBusinessRule(account,amount);
    }

    @Test
    public void validateBusinessRule_amountLessThanBalance_shouldNotException() {
        //given
        BigDecimal amount = new BigDecimal("1000");
        when(account.getBalance()).thenReturn(new BigDecimal("40000"));
        //when
        transferMoneyUseCase.validateBusinessRule(account,amount);
        //then
        verify(account).getBalance();
    }

    @Test(expected = LowBalanceException.class)
    public void executeInTransaction_amountMoreThanBalance_shouldThrowLowBalanceException() {
        //given
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("1234","2345",new BigDecimal("100000"));
        doThrow(new LowBalanceException(" [1234,100000]")).when(transferMoneyUseCase).validateBusinessRule(any(),any());
        //when
        transferMoneyUseCase.executeInTransaction(transferMoneyRequest);
    }

    @Test(expected = EmptyAccountNumberException.class)
    public void executeInTransaction_emptyAccountNumber_shouldThrowEmptyAccountNumberException() {
        //given
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("","2345",new BigDecimal("100000"));
        doThrow(new EmptyAccountNumberException()).when(transferMoneyUseCase).validateRequestData(transferMoneyRequest);
        //when
        transferMoneyUseCase.executeInTransaction(transferMoneyRequest);
    }
}