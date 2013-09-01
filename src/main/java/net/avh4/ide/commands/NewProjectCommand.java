package net.avh4.ide.commands;

import net.avh4.ide.GlobalCommand;
import net.avh4.ide.HdeModel;
import net.avh4.ide.ProjectWindowFactory;
import net.avh4.ide.Window;
import net.avh4.ide.ui.WindowManager;

public class NewProjectCommand implements GlobalCommand {
    private final WindowManager windowManager;
    private final ProjectWindowFactory projectWindowFactory;
    private final HdeModel model;

    public NewProjectCommand(WindowManager windowManager,
                             ProjectWindowFactory projectWindowFactory, HdeModel model) {
        this.model = model;
        this.windowManager = windowManager;
        this.projectWindowFactory = projectWindowFactory;
    }

    @Override
    public void execute() {
        model.createNewProject(null);
        Window defaultWindow = projectWindowFactory.getDefaultWindow(model.project());
        windowManager.showWindow(defaultWindow);
    }
}
