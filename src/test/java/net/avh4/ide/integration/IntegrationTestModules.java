package net.avh4.ide.integration;

import net.avh4.ide.GlobalModule;
import net.avh4.ide.WelcomeWindowView;
import net.avh4.ide.editing.EditorWindowView;
import net.avh4.ide.platforms.android.AndroidSdk;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.PicoContainer;

public class IntegrationTestModules {
    public static PicoContainer uiModule() {
        MutablePicoContainer pico = new DefaultPicoContainer();

        GlobalModule.configureContainer(pico);

        pico.removeComponent(AndroidSdk.class);
        pico.addComponent(new TestAndroidSdk());
        pico.addComponent(new TestWindowManager());
        pico.addComponent(new TestKeyboardInputSource());
        pico.addAdapter(TestWindowViewAdapter.create(EditorWindowView.class));
        pico.addAdapter(TestWindowViewAdapter.create(WelcomeWindowView.class));

        pico.addComponent(UiAgent.class);
        return pico;
    }

    public static PicoContainer domainModule() {
        MutablePicoContainer pico = new DefaultPicoContainer();
        GlobalModule.configureContainer(pico);
        pico.addComponent(DomainAgent.class);

        pico.removeComponent(AndroidSdk.class);
        pico.addComponent(new TestAndroidSdk());

        return pico;
    }
}
