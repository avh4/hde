package net.avh4.ide;

import com.google.common.collect.ImmutableSet;
import net.avh4.ide.commands.NewProjectCommand;
import net.avh4.ide.ui.WindowManager;

public class WelcomeWindow implements Window<WelcomeWindow.Actions> {
    private final WelcomeWindowView view;

    public static interface Actions {
        public void tapNewAndroidProject();

        public void tapOpenProject();
    }

    public WelcomeWindow(final WindowManager windowManager, final NewProjectCommand newProjectCommand,
                         WelcomeWindowView view) {
        this.view = view;
        view.setActions(new Actions() {
            @Override
            public void tapNewAndroidProject() {
                windowManager.dismissWindow(WelcomeWindow.this);
                newProjectCommand.execute();
            }

            @Override
            public void tapOpenProject() {
                throw new RuntimeException("Not implemented");  // TODO
            }
        });
    }

    @Override
    public ImmutableSet<Function> functions() {
        throw new RuntimeException("Not implemented");
    }

    @Override
    public WindowView<Actions> view() {
        return view;
    }
}
