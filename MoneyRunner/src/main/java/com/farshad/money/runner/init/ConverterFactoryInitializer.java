package com.farshad.money.runner.init;

import com.farshad.money.controller.converter.ConverterFactory;
import com.farshad.money.controller.converter.impl.ConverterFactoryImpl;

class ConverterFactoryInitializer {
    private static ConverterFactoryInitializer ourInstance = new ConverterFactoryInitializer();

    static ConverterFactoryInitializer getInstance() {
        return ourInstance;
    }

    private ConverterFactory converterFactory;

    private ConverterFactoryInitializer() {
        converterFactory = new ConverterFactoryImpl();
    }

    ConverterFactory getConverterFactory() {
        return converterFactory;
    }
}
