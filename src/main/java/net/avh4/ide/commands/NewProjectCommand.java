package net.avh4.ide.commands;

import net.avh4.data.per2.Database;
import net.avh4.ide.*;
import net.avh4.ide.platforms.CodeProject;
import net.avh4.ide.ui.WindowManager;

import static com.google.common.base.Preconditions.checkNotNull;

public class NewProjectCommand implements GlobalCommand {
    private final ProjectTemplates projectTemplates;
    private final WindowManager windowManager;
    private final ProjectWindowFactory projectWindowFactory;
    private final KeyboardHandler keyboardHandler;
    private final Database db;

    public NewProjectCommand(ProjectTemplates projectTemplates, WindowManager windowManager,
                             ProjectWindowFactory projectWindowFactory, KeyboardHandler keyboardHandler, Database db) {
        this.keyboardHandler = keyboardHandler;
        this.db = db;
        this.projectTemplates = checkNotNull(projectTemplates);
        this.windowManager = windowManager;
        this.projectWindowFactory = projectWindowFactory;
    }

    @Override
    public void execute() {
        final CodeProject project = projectTemplates.createAndroidHelloWorldProject(db);
        Window defaultWindow = projectWindowFactory.getDefaultWindow(project);
        windowManager.showWindow(defaultWindow);
        keyboardHandler.setProject(project);
    }
}
