package com.farshad.money.app.usecase;

import com.farshad.money.app.entity.Account;
import com.farshad.money.ports.database.AccountGateway;
import com.farshad.money.ports.usecase.exception.Messages;
import com.farshad.money.ports.usecase.exception.UseCaseException;
import com.farshad.money.ports.usecase.request.TransferMoneyRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.math.BigDecimal;

import static org.junit.Assert.*;
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

    @Test(expected = UseCaseException.class)
    public void validateRequestData_emptyAccountNumber_shouldThrowUseCaseException() {
        //given
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("","1234",new BigDecimal("230"));
        //when
        transferMoneyUseCase.validateRequestData(transferMoneyRequest);
    }

    @Test(expected = UseCaseException.class)
    public void validateRequestData_emptyDestinationAccountNumber_shouldThrowUseCaseException() {
        //given
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("1234","",new BigDecimal("230"));
        //when
        transferMoneyUseCase.validateRequestData(transferMoneyRequest);
    }

    @Test(expected = UseCaseException.class)
    public void validateRequestData_zeroTransferAmount_shouldThrowUseCaseException() {
        //given
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("1234","2345",new BigDecimal("0"));
        //when
        transferMoneyUseCase.validateRequestData(transferMoneyRequest);
    }

    @Test
    public void validateRequestData_validData_shouldNotThrowUseCaseException() {
        //given
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("1234","2345",new BigDecimal("900"));
        //when
        transferMoneyUseCase.validateRequestData(transferMoneyRequest);
    }

    @Test(expected = UseCaseException.class)
    public void validateBusinessRule_amountMoreThanBalance_shouldThrowUseCaseException() {
        //given
        BigDecimal amount = new BigDecimal("100000");
        when(account.getBalance()).thenReturn(new BigDecimal("40000"));
        //when
        transferMoneyUseCase.validateBusinessRule(account,amount);
    }

    @Test
    public void validateBusinessRule_amountLessThanBalance_shouldNotThrowUseCaseException() {
        //given
        BigDecimal amount = new BigDecimal("1000");
        when(account.getBalance()).thenReturn(new BigDecimal("40000"));
        //when
        transferMoneyUseCase.validateBusinessRule(account,amount);
        //then
        verify(account).getBalance();
    }

    @Test(expected = UseCaseException.class)
    public void executeInTransaction_amountMoreThanBalance_shouldThrowUseCaseException() {
        //given
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("1234","2345",new BigDecimal("100000"));
        doThrow(new UseCaseException(Messages.BALANCE_IS_LOW)).when(transferMoneyUseCase).validateBusinessRule(any(),any());
        //when
        transferMoneyUseCase.executeInTransaction(transferMoneyRequest);
    }

    @Test(expected = UseCaseException.class)
    public void executeInTransaction_emptyAccountNumber_shouldThrowUseCaseException() {
        //given
        TransferMoneyRequest transferMoneyRequest = new TransferMoneyRequest("","2345",new BigDecimal("100000"));
        doThrow(new UseCaseException(Messages.ACCOUNT_NUMBER_IS_EMPTY)).when(transferMoneyUseCase).validateRequestData(transferMoneyRequest);
        //when
        transferMoneyUseCase.executeInTransaction(transferMoneyRequest);
    }
}