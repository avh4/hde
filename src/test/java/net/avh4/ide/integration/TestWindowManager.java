package net.avh4.ide.integration;

import net.avh4.ide.Window;
import net.avh4.ide.ui.WindowManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class TestWindowManager implements WindowManager {
    private Window visibleWindow;

    @Override
    public void showWindow(Window<?> window) {
        checkNotNull(window);
        this.visibleWindow = window;
    }

    @Override
    public void dismissWindow(Window<?> window) {
        if (window == visibleWindow) {
            visibleWindow = null;
        } else {
            throw new RuntimeException("dismissWindow: window was not visible: " + window);
        }
    }

    public Window visibleWindow() {
        return visibleWindow;
    }

    public <A, T extends Window<A>> A find(Class<T> windowClass) {
        if (visibleWindow == null) {
            throw new RuntimeException("No visible windows");
        }
        if (windowClass.isAssignableFrom(visibleWindow.getClass())) {
            //noinspection unchecked
            T window = (T) visibleWindow;
            return window.view().actions();
        } else {
            throw new RuntimeException("Cannot find a " + windowClass.getSimpleName() + " in the set of visible windows:\n" +
                    "    " + visibleWindow.getClass().getCanonicalName());
        }
    }
}
