package com.farshad.money.runner;

import com.farshad.money.controller.CreateAccountController;
import com.farshad.money.controller.TransferMoneyController;
import com.farshad.money.controller.dto.BaseResponseDto;
import com.farshad.money.controller.dto.CreateAccountRequestDto;
import com.farshad.money.controller.dto.TransferMoneyRequestDto;
import com.farshad.money.runner.init.Initializer;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static spark.Spark.get;
import static spark.Spark.post;

public class SparkRunner {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(SparkRunner.class);

        logger.info("initializing.....");
        Initializer initializer = Initializer.getInstance();
        TransferMoneyController transferMoneyController = initializer.getControllerFactory().transferMoneyController();
        CreateAccountController createAccountController = initializer.getControllerFactory().createAccountController();
        initializer.initTestData();
        Gson gson = new Gson();
        logger.info("Now ready .....");

        get("/health", (request, response) -> "I'm OK");
        post("/transfer", (request, response) -> {
            response.type("application/json");
            TransferMoneyRequestDto transferMoneyRequestDto = gson.fromJson(request.body(), TransferMoneyRequestDto.class);
            BaseResponseDto baseResponseDto = transferMoneyController.transferMoney(transferMoneyRequestDto);
            if(baseResponseDto.getErrorMessage()!=null){
                response.status(400);
            }
            return  gson.toJson(baseResponseDto);
        });
        post("/account", (request, response) -> {
            response.type("application/json");
            CreateAccountRequestDto createAccountRequestDto = gson.fromJson(request.body(), CreateAccountRequestDto.class);
            BaseResponseDto baseResponseDto = createAccountController.createAccount(createAccountRequestDto);
            if(baseResponseDto.getErrorMessage()!=null){
                response.status(400);
            }
            return  gson.toJson(baseResponseDto);
        });
    }


}
