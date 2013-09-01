package net.avh4.ide;

import net.avh4.ide.editing.EditorWindowProvider;
import net.avh4.ide.platforms.CodeClass;
import net.avh4.ide.platforms.CodeModule;
import net.avh4.ide.platforms.CodeProject;

public class ProjectWindowFactory {
    private final EditorWindowProvider provider;

    public ProjectWindowFactory(EditorWindowProvider provider) {
        this.provider = provider;
    }

    public Window getDefaultWindow(CodeProject newProject) {
        final CodeModule module = newProject.modules()[0];
        final CodeClass codeClass = module.classes()[0];
        return provider.get(codeClass);
    }
}
