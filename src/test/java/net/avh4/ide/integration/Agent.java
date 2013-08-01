package net.avh4.ide.integration;

import net.avh4.ide.Application;
import net.avh4.ide.Function;
import net.avh4.ide.Window;
import net.avh4.ide.editing.EditorWindow;

public class Agent {
    private Application app;

    public void createNewProject(String projectName) {
        app = new Application();
    }

    public boolean isEditorVisible() {
        Window w = app.visibleWindow();
        if (w == null) throw new RuntimeException("No visible windows");
        return w.functions().contains(Function.EDITING);
    }

    public String editorContents() {
        Window w =app.visibleWindow();
        if (! (w instanceof EditorWindow)) throw new RuntimeException("Don't know how to find the Editor window (if there is one)");
        EditorWindow e = (EditorWindow) w;
        return e.getContents();
    }
}
