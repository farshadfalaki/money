package com.farshad.money.app.usecase;

import com.farshad.money.ports.database.TransactionalRunner;
import com.farshad.money.ports.usecase.CommandUseCase;

public abstract class TransactionalCommandUseCase<T> implements
		CommandUseCase<T>
{
	private final TransactionalRunner transactionalRunner;

	public TransactionalCommandUseCase(TransactionalRunner transactionalRunner) {
		this.transactionalRunner = transactionalRunner;
	}
	
	@Override
	public final void execute(T request) {
		transactionalRunner.executeInTransaction(() -> executeInTransaction(request));
	}

	protected abstract void executeInTransaction(T request);
	
}
