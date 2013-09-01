package net.avh4.ide;

import net.avh4.ide.commands.NewProjectCommand;
import net.avh4.ide.ui.WindowManager;
import net.avh4.test.junit.Nested;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.verify;

@RunWith(Nested.class)
public class WelcomeWindowTest {
    private WelcomeWindow subject;
    @Mock private WindowManager windowManager;
    @Mock private NewProjectCommand newProjectCommand;
    @Mock private WelcomeWindowView view;
    private WelcomeWindow.Actions actions;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        subject = new WelcomeWindow(windowManager, newProjectCommand, view);
        actions = subject.actions();
    }

    @Test
    public void shouldHaveView() throws Exception {
        assertThat(subject.view()).isSameAs(view);
    }

    public class TapNewAndroidProject {
        @Before
        public void setUp() throws Exception {
            actions.tapNewAndroidProject();
        }

        @Test
        public void shouldDismissItself() throws Exception {
            verify(windowManager).dismissWindow(subject);
        }

        @Test
        public void shouldCreateTheNewProject() throws Exception {
            verify(newProjectCommand).execute();
        }
    }
}
