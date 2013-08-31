package net.avh4.ide.integration;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;

public abstract class IntegrationTestModule {
    private final MutablePicoContainer pico;

    IntegrationTestModule(Class<? extends Agent> agentClass) {
        this.pico = new DefaultPicoContainer();
        pico.addComponent(agentClass);
        configure(pico);
    }

    public final PicoContainer getContainer() {
        return pico;
    }

    protected abstract void configure(MutablePicoContainer pico);
}
