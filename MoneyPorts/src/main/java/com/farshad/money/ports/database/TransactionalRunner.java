package com.farshad.money.ports.database;

public interface TransactionalRunner {
    void executeInTransaction(Runnable runnable);
}
