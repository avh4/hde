package net.avh4.ide.struct;

import javax.transaction.TransactionRequiredException;

public class STM {
    private static boolean inTransaction;

    public static void transaction(Transaction transaction) {
        inTransaction = true;
        try {
            transaction.run();
        } catch (TransactionRequiredException e) {
            throw new RuntimeException("TransactionRequiredException while in transaction", e);
        }
        inTransaction = false;
    }

    public static void requireTransaction() throws TransactionRequiredException {
        if (!inTransaction) throw new TransactionRequiredException();
    }
}
