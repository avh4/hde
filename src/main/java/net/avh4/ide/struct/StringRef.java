package net.avh4.ide.struct;

import net.avh4.ide.struct.example.Watch;

import javax.transaction.TransactionRequiredException;

public class StringRef extends Ref<String> {
    public StringRef() {
    }

    public StringRef(Watch<? super String> initialWatch) {
        super(initialWatch);
    }

    @Override
    public void set(String value) throws TransactionRequiredException {
        super.set(value);
    }
}
