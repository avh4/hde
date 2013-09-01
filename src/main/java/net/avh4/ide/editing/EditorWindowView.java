package net.avh4.ide.editing;

import net.avh4.ide.WindowView;

import java.io.IOException;

public interface EditorWindowView extends WindowView<EditorWindow.Actions> {
    void setContent(String description, String content);
}
