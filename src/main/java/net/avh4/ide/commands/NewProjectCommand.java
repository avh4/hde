package net.avh4.ide.commands;

import net.avh4.ide.*;
import net.avh4.ide.platforms.CodeProject;
import net.avh4.ide.ui.WindowManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class NewProjectCommand implements GlobalCommand {
    private final ProjectTemplates projectTemplates;
    private final WindowManager windowManager;
    private final ProjectWindowFactory projectWindowFactory;
    private final KeyboardHandler keyboardHandler;

    public NewProjectCommand(ProjectTemplates projectTemplates, WindowManager windowManager,
                             ProjectWindowFactory projectWindowFactory, KeyboardHandler keyboardHandler) {
        this.keyboardHandler = keyboardHandler;
        this.projectTemplates = checkNotNull(projectTemplates);
        this.windowManager = windowManager;
        this.projectWindowFactory = projectWindowFactory;
    }

    @Override
    public void execute() {
        final CodeProject project = projectTemplates.createAndroidHelloWorldProject();
        Window defaultWindow = projectWindowFactory.getDefaultWindow(project);
        windowManager.showWindow(defaultWindow);
        keyboardHandler.setProject(project);
    }
}
