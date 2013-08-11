package net.avh4.ide;

import net.avh4.ide.ui.swing.SwingModule;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

public class ApplicationDebug {
    public static void main(String[] args) {
        MutablePicoContainer pico = new DefaultPicoContainer();
        GlobalModule.configureContainer(pico);
        SwingModule.configureContainer(pico);
        Application a = pico.getComponent(ApplicationProvider.class).get();
        a.start();
    }
}
