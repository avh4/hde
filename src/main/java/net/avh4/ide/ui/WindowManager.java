package net.avh4.ide.ui;

import net.avh4.ide.Window;

public interface WindowManager {
    void showWindow(Window<?> window);

    void dismissWindow(Window<?> subject);
}
