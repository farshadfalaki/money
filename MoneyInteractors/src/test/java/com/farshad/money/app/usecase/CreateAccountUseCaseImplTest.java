package com.farshad.money.app.usecase;

import com.farshad.money.app.entity.Account;
import com.farshad.money.ports.persistence.AccountGateway;
import com.farshad.money.ports.usecase.exception.UseCaseException;
import com.farshad.money.ports.usecase.request.CreateAccountRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class CreateAccountUseCaseImplTest {
    @Mock
    Account.AccountFactory accountFactory;
    @Mock
    AccountGateway accountGateway;
    @InjectMocks
    CreateAccountUseCaseImpl createAccountUseCase;

    @Test
    public void executeInTransaction() {
    }

    @Test(expected = UseCaseException.class)
    public void validateRequestData_emptyAccountNumber_shouldThrowUseCaseException() {
        //given
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("","Alex",new BigDecimal("400"));
        //when
        createAccountUseCase.validateRequestData(createAccountRequest);
    }

    @Test(expected = UseCaseException.class)
    public void validateRequestData_emptyName_shouldThrowUseCaseException() {
        //given
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234","",new BigDecimal("400"));
        //when
        createAccountUseCase.validateRequestData(createAccountRequest);
    }

    @Test(expected = UseCaseException.class)
    public void validateRequestData_minusOneBalance_shouldThrowUseCaseException() {
        //given
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234","Andre",new BigDecimal("-1"));
        //when
        createAccountUseCase.validateRequestData(createAccountRequest);
    }

    @Test
    public void validateRequestData_validRequest_shouldNotThrowUseCaseException() {
        //given
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234","Andre",new BigDecimal("1"));
        //when
        createAccountUseCase.validateRequestData(createAccountRequest);
    }

    @Test
    public void setFields_validData_shouldAllFieldsBeTheSame() {
        //given
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234","Andre",new BigDecimal("1"));
        Account actualAccount = new AccountDummyImpl();
        Account expectedAccount = new AccountDummyImpl(0,"1234","Andre",new BigDecimal("1"));
        //when
        createAccountUseCase.setFields(actualAccount,createAccountRequest);
        //then
        assertEquals(expectedAccount,actualAccount);
    }
}