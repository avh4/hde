package net.avh4.ide;

import net.avh4.ide.commands.ExecuteProjectCommand;
import net.avh4.ide.platforms.CodeProject;
import net.avh4.test.junit.Nested;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(Nested.class)
public class KeyboardHandlerTest {
    private KeyboardHandler subject;
    @Mock private ExecuteProjectCommand executeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new KeyboardHandler(executeCommand);
    }

    public class WithAProject {
        @Mock
        private CodeProject project;

        @Before
        public void setUp() throws Exception {
            MockitoAnnotations.initMocks(this);
            subject.setProject(project);
        }

        @Test
        public void shouldExecuteProject() throws Exception {
            subject.sendKeyStroke("⇧⌘⑩");

            verify(executeCommand).execute(project);
        }

        @Test
        public void shouldIgnoreUnknownKeystrokes() throws Exception {
            subject.sendKeyStroke("⌘V");

            verifyNoMoreInteractions(executeCommand);
        }
    }
}
