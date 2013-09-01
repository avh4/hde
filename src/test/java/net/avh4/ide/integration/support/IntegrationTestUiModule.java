package net.avh4.ide.integration.support;

import net.avh4.ide.WelcomeWindowView;
import net.avh4.ide.editing.EditorWindowView;
import net.avh4.ide.test.support.TestKeyboardInputSource;
import net.avh4.ide.test.support.TestWindowManager;
import net.avh4.ide.test.support.TestWindowViewAdapter;
import org.picocontainer.MutablePicoContainer;

public class IntegrationTestUiModule extends IntegrationTestDomainModule {
    @Override
    protected Class<? extends Agent> getAgentClass() {
        return UiAgent.class;
    }

    @Override
    protected void configure(MutablePicoContainer pico) {
        super.configure(pico);

        pico.addComponent(new TestWindowManager());
        pico.addComponent(new TestKeyboardInputSource());
        pico.addAdapter(TestWindowViewAdapter.create(EditorWindowView.class));
        pico.addAdapter(TestWindowViewAdapter.create(WelcomeWindowView.class));
    }
}
