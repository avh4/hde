package net.avh4.ide;

import net.avh4.ide.commands.NewProjectCommand;
import net.avh4.ide.editing.EditorWindowProvider;
import org.picocontainer.Characteristics;
import org.picocontainer.MutablePicoContainer;

public abstract class ApplicationModule {
    public static void configureContainer(MutablePicoContainer pico) {
        pico.as(Characteristics.CACHE).addComponent(Application.class);
        pico.as(Characteristics.CACHE).addComponent(HdeModel.class);

        pico.addComponent(NewProjectCommand.class);
        pico.addComponent(WelcomeWindow.class);
        pico.addComponent(KeyboardHandler.class);
        pico.addComponent(ProjectWindowFactory.class);
        pico.addComponent(EditorWindowProvider.class);
    }
}
