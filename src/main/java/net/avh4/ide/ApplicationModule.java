package net.avh4.ide;

import net.avh4.ide.commands.NewProjectCommand;
import org.picocontainer.MutablePicoContainer;

public abstract class ApplicationModule {
    public static void configureContainer(MutablePicoContainer pico) {
        pico.addComponent(NewProjectCommand.class);
        pico.addComponent(WelcomeWindow.class);
        pico.addComponent(KeyboardHandler.class);
    }
}
