package net.avh4.ide.integration;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;

public abstract class IntegrationTestModule {
    private final MutablePicoContainer pico;

    IntegrationTestModule() {
        this.pico = new DefaultPicoContainer();
        pico.addComponent(getAgentClass());
        configure(pico);
    }

    protected abstract Class<? extends Agent> getAgentClass();

    public final PicoContainer getContainer() {
        return pico;
    }

    protected abstract void configure(MutablePicoContainer pico);
}
