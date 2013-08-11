package net.avh4.ide.ui.swing;

import net.avh4.ide.editing.SwingEditorWindowView;
import org.picocontainer.MutablePicoContainer;

public class SwingModule {
    public static void configureContainer(MutablePicoContainer pico) {
        pico.addComponent(new SwingWindowManager());
        pico.addComponent(new SwingKeyboardInputSource());
        pico.addComponent(SwingWelcomeWindowView.class);
        pico.addComponent(SwingEditorWindowView.class);
    }
}
