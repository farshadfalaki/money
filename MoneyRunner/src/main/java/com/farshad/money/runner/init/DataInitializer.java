package com.farshad.money.runner.init;

import com.farshad.money.ports.usecase.CommandUseCase;
import com.farshad.money.ports.usecase.UseCaseFactories;
import com.farshad.money.ports.usecase.exception.UseCaseException;
import com.farshad.money.ports.usecase.request.CreateAccountRequest;

import java.math.BigDecimal;

class DataInitializer {
    private UseCaseFactories useCaseFactories;

    DataInitializer(UseCaseFactories useCaseFactories) {
        this.useCaseFactories = useCaseFactories;
    }

    void initData(){
        CommandUseCase<CreateAccountRequest> createAccountUseCase = useCaseFactories.createAccountUseCase();
       try {
            CreateAccountRequest createAccountRequest = new CreateAccountRequest("1010", "Bob", new BigDecimal(500000));
            createAccountUseCase.execute(createAccountRequest);
            createAccountRequest = new CreateAccountRequest("2020", "Sara", new BigDecimal(500000));
            createAccountUseCase.execute(createAccountRequest);
        }catch (UseCaseException e){
            System.out.println("error while initData"+ e.getMessage());
        }
    }
}
