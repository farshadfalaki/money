package com.farshad.money.runner.init;

import com.farshad.money.controller.ControllerFactory;
import com.farshad.money.controller.converter.ConverterFactory;
import com.farshad.money.ports.usecase.UseCaseFactories;

public class Initializer {
    private UseCaseFactories    useCaseFactories;
    private ControllerFactory   controllerFactory;

    private static Initializer ourInstance = new Initializer();

    public static Initializer getInstance() {
        return ourInstance;
    }

    private Initializer() {
        useCaseFactories  = UseCaseFactoryInitializer.getInstance().getUseCaseFactories();
        ConverterFactory converterFactory = ConverterFactoryInitializer.getInstance().getConverterFactory();
        controllerFactory = ControllerFactoryInitializer.getInstance().getControllerFactory(useCaseFactories, converterFactory);
    }

    public ControllerFactory getControllerFactory() {
        return controllerFactory;
    }

    public void initTestData(){
        new DataInitializer(useCaseFactories).initData();
    }
}
