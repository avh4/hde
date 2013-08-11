package net.avh4.ide.struct;

import javax.transaction.TransactionRequiredException;

public interface Transaction {
    public void run() throws TransactionRequiredException;
}
