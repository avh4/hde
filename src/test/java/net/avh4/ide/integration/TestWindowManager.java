package net.avh4.ide.integration;

import net.avh4.ide.Window;
import net.avh4.ide.ui.WindowManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class TestWindowManager implements WindowManager {
    private Window visibleWindow;

    @Override
    public <A> void showWindow(Window<A> window) {
        checkNotNull(window);
        window.view().setActions(window.actions());
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

    public <T extends Window> T find(Class<T> windowClass) {
        if (visibleWindow == null) {
            throw new RuntimeException("No visible windows");
        }
        if (windowClass.isAssignableFrom(visibleWindow.getClass())) {
            //noinspection unchecked
            return (T) visibleWindow;
        } else {
            throw new RuntimeException("Cannot find a " + windowClass.getSimpleName() + " in the set of visible windows:\n" +
                    "    " + visibleWindow.getClass().getCanonicalName());
        }
    }
}
