package com.farshad.money.adapters.database.dao;

import com.farshad.money.adapters.database.model.JPAAccount;
import javax.persistence.EntityManager;

public class JPAAccountDao {

    private EntityManager entityManager;

    public JPAAccountDao(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void persist(JPAAccount jpaAccount) {
        entityManager().persist(jpaAccount);
    }

    private EntityManager entityManager() {
        return entityManager;
    }

    public JPAAccount findAccountByAccountNumber(String accountNumber) {
        return entityManager()
                .createQuery("select model from JPAAccount model where model.accountNumber = :accountNumber",JPAAccount.class)
                .setParameter("accountNumber",accountNumber).getSingleResult();
    }
}
