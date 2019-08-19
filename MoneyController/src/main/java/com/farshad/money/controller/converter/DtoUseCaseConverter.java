package com.farshad.money.controller.converter;

public interface DtoUseCaseConverter<Q,S> {
    S convert(Q q);
}
