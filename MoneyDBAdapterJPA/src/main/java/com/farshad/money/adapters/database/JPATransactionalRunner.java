package com.farshad.money.adapters.database;

import com.farshad.money.ports.persistence.TransactionalRunner;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class JPATransactionalRunner implements TransactionalRunner {
	public JPATransactionalRunner(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	private EntityManager entityManager;

	@Override
	public void executeInTransaction(Runnable runnable) {
		EntityTransaction transaction = beginTransaction();
		try {
			runnable.run();
			transaction.commit();

		} catch (Throwable e) {
			if(transaction.isActive())
				transaction.rollback();
			throw e;
		}finally {
			entityManager.clear();
		}
	}
	
	private EntityTransaction beginTransaction() {
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		return transaction;
	}

}
