package com.farshad.money.runner.init;

import com.farshad.money.adapters.database.JPAAccountFactory;
import com.farshad.money.adapters.database.JPATransactionalRunner;
import com.farshad.money.adapters.database.dao.JPAAccountDao;
import com.farshad.money.adapters.database.dao.JPAAccountGateway;
import com.farshad.money.app.entity.Account;
import com.farshad.money.app.usecase.UseCaseFactoriesImpl;
import com.farshad.money.ports.persistence.AccountGateway;
import com.farshad.money.ports.persistence.TransactionalRunner;
import com.farshad.money.ports.usecase.UseCaseFactories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

class UseCaseFactoryInitializer {
    private static UseCaseFactoryInitializer ourInstance = new UseCaseFactoryInitializer();

    static UseCaseFactoryInitializer getInstance() {
        return ourInstance;
    }

    private UseCaseFactories useCaseFactories;

    private UseCaseFactoryInitializer() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("h2-db");
        EntityManager em = emf.createEntityManager();
        JPAAccountDao jpaAccountDao = new JPAAccountDao(em);
        AccountGateway accountGateway = new JPAAccountGateway(jpaAccountDao);
        TransactionalRunner transactionalRunner = new JPATransactionalRunner(em);
        Account.AccountFactory accountFactory = new JPAAccountFactory();
        useCaseFactories = new UseCaseFactoriesImpl(accountGateway,transactionalRunner,accountFactory);
    }

    UseCaseFactories getUseCaseFactories() {
        return useCaseFactories;
    }
}
