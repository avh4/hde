package net.avh4.ide.struct.example;

import net.avh4.ide.struct.StringRef;

import javax.transaction.TransactionRequiredException;

public class CodeModule {
    private final StringRef nameRef = new StringRef();

    public void watchName(Watch<String> watch) {
        nameRef.watch(watch);
    }

    public void setName(String name) throws TransactionRequiredException {
        nameRef.set(name);
    }
}
