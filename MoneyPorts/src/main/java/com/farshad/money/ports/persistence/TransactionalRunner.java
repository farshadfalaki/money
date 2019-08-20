package com.farshad.money.ports.persistence;

public interface TransactionalRunner {
    void executeInTransaction(Runnable runnable);
}
