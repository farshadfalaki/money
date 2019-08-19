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

    public boolean checkAccountExistenceByAccountNumber(String accountNumber) {
        boolean accountNumberExistenceFlag = false;
        Long resultCount =  entityManager()
                .createQuery("select count(model.id) from JPAAccount model where model.accountNumber = :accountNumber",Long.class)
                .setParameter("accountNumber",accountNumber).getSingleResult();
        if(resultCount==1){
            accountNumberExistenceFlag = true ;
        }
        return accountNumberExistenceFlag;

    }
}
