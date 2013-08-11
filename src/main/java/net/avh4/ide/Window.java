package net.avh4.ide;

import com.google.common.collect.ImmutableSet;

public interface Window<A> {
    ImmutableSet<Function> functions();

    WindowView<A> view();
}
