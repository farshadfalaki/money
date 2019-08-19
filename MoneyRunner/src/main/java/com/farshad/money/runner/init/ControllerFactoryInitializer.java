package com.farshad.money.runner.init;

import com.farshad.money.controller.ControllerFactory;
import com.farshad.money.controller.ControllerFactoryImpl;
import com.farshad.money.controller.converter.ConverterFactory;
import com.farshad.money.ports.usecase.UseCaseFactories;

class ControllerFactoryInitializer {
    private static ControllerFactoryInitializer ourInstance = new ControllerFactoryInitializer();

    static ControllerFactoryInitializer getInstance() {
        return ourInstance;
    }

    private ControllerFactoryInitializer() {}
    private ControllerFactory controllerFactory;

    ControllerFactory getControllerFactory(UseCaseFactories useCaseFactories, ConverterFactory converterFactory) {
        if(controllerFactory == null) {
            controllerFactory = new ControllerFactoryImpl(useCaseFactories, converterFactory);
        }
        return controllerFactory;
    }
}
