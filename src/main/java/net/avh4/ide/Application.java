package net.avh4.ide;

import net.avh4.ide.editing.EditorWindow;

public class Application {
    public Window visibleWindow() {
        return new EditorWindow();
    }
}
