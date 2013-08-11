package net.avh4.ide.struct;

import com.google.common.collect.ImmutableList;

import javax.transaction.TransactionRequiredException;

public class ListRef<T> extends Ref<ImmutableList<T>> {
    public ListRef() {
        super(ImmutableList.<T>of());
    }

    public void add() throws TransactionRequiredException {

    }

    public void add(T value) throws TransactionRequiredException {
        STM.requireTransaction();
        final ImmutableList<T> newValue = ImmutableList.<T>builder()
                .addAll(get())
                .add(value)
                .build();
        set(newValue);
    }
}
