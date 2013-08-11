package net.avh4.ide;

import org.picocontainer.Characteristics;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;

public class ApplicationProvider {
    private final PicoContainer pico;

    public ApplicationProvider(PicoContainer pico) {
        this.pico = pico;
    }

    public Application get() {
        MutablePicoContainer p = new DefaultPicoContainer(pico);
        ApplicationModule.configureContainer(p);
        scopeComponent(p, KeyboardHandler.class);
        p.as(Characteristics.CACHE).addComponent(Application.class);
        return p.getComponent(Application.class);
    }

    private static void scopeComponent(MutablePicoContainer p, Class<?> componentType) {
        final Object component = p.getComponent(componentType);
        p.removeComponent(componentType);
        p.addComponent(component);
    }
}
