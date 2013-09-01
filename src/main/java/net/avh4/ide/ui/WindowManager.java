package net.avh4.ide.ui;

import net.avh4.ide.Window;

public interface WindowManager {
    <A> void showWindow(Window<A> window);

    void dismissWindow(Window<?> subject);
}
