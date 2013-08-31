package net.avh4.ide.integration;

import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;

public class IntegrationTestModules {
    public static PicoContainer uiModule() {
        MutablePicoContainer pico = new DefaultPicoContainer();
        pico.addComponent(UiAgent.class);
        return pico;
    }

    public static PicoContainer domainModule() {
        MutablePicoContainer pico = new DefaultPicoContainer();
        pico.addComponent(DomainAgent.class);
        return pico;
    }
}
