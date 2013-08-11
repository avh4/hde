package net.avh4.ide;

import net.avh4.ide.ui.WindowManager;
import net.avh4.test.junit.Nested;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

@RunWith(Nested.class)
public class ApplicationTest {
    private Application subject;
    @Mock private WindowManager windowManager;
    @Mock private WelcomeWindow welcomeWindow;
    @Mock private KeyboardInputSource keyboardInputSource;
    @Mock private KeyboardHandler keyboardHandler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new Application(windowManager, welcomeWindow, keyboardInputSource, keyboardHandler);
    }

    public class Start {
        @Before
        public void setUp() throws Exception {
            subject.start();
        }

        @Test
        public void shouldShowWelcomeWindow() throws Exception {
            verify(windowManager).showWindow(welcomeWindow);
        }

        @Test
        public void shouldConnectKeyboardHandler() throws Exception {
            verify(keyboardInputSource).addKeyboardInputSourceListener(keyboardHandler);
        }

        @Test
        public void shouldStartTheKeyboardInputSource() throws Exception {
            verify(keyboardInputSource).start();
        }
    }
}
