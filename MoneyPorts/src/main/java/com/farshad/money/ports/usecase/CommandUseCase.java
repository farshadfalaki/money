package com.farshad.money.ports.usecase;

public interface CommandUseCase<T> {
	void execute(T request);
}
