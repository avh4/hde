package net.avh4.ide.integration;

import net.avh4.ide.GlobalModule;
import net.avh4.ide.HdeModel;
import net.avh4.ide.platforms.android.AndroidSdk;
import org.picocontainer.Characteristics;
import org.picocontainer.MutablePicoContainer;

public class IntegrationTestDomainModule extends IntegrationTestModule {
    @Override
    protected Class<? extends Agent> getAgentClass() {
        return DomainAgent.class;
    }

    @Override
    protected void configure(MutablePicoContainer pico) {
        GlobalModule.configureContainer(pico);

        pico.as(Characteristics.CACHE).addComponent(HdeModel.class);

        pico.removeComponent(AndroidSdk.class);
        pico.addComponent(new TestAndroidSdk());
    }
}
