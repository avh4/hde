package net.avh4.ide.struct;

import fj.data.List;
import net.avh4.ide.struct.example.Watch;

import javax.transaction.TransactionRequiredException;

public class Ref<T> {
    private List<Watch<? super T>> watchers = List.nil();
    protected T value;

    public Ref() {
    }

    public Ref(Watch<? super T> initialWatch) {
        watch(initialWatch);
    }

    public Ref(T initial) {
        value = initial;
    }

    public void watch(Watch<? super T> watch) {
        watchers = watchers.cons(watch);
        if (value == null) return;
        notifyWatchers();
    }

    protected final T get() {
        return value;
    }

    protected void set(T value) throws TransactionRequiredException {
        STM.requireTransaction();
        this.value = value;
        notifyWatchers();
    }

    protected void notifyWatchers() {
        for (Watch<? super T> watcher : watchers) {
            watcher.update(value);
        }
    }
}
