package net.avh4.ide.commands;

import net.avh4.ide.KeyboardHandler;
import net.avh4.ide.ProjectTemplates;
import net.avh4.ide.ProjectWindowFactory;
import net.avh4.ide.Window;
import net.avh4.ide.platforms.CodeProject;
import net.avh4.ide.ui.WindowManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class NewProjectCommandTest {
    private NewProjectCommand subject;
    @Mock
    private ProjectTemplates projectTemplates;
    @Mock
    private CodeProject newProject;
    @Mock
    private WindowManager windowManager;
    @Mock
    private Window projectWindow;
    @Mock
    private ProjectWindowFactory projectWindowFactory;
    @Mock private KeyboardHandler keyboardHandler;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(projectTemplates.createAndroidHelloWorldProject()).toReturn(newProject);
        stub(projectWindowFactory.getDefaultWindow(newProject)).toReturn(projectWindow);
        subject = new NewProjectCommand(projectTemplates, windowManager, projectWindowFactory, keyboardHandler);
        subject.execute();
    }

    @Test
    public void shouldMakeANewProject() throws Exception {
        verify(projectTemplates).createAndroidHelloWorldProject();
    }

    @Test
    public void shouldShowAnEditorWindow() throws Exception {
        verify(windowManager).showWindow(projectWindow);
    }

    @Test
    public void shouldSetProjectWithTheKeyboardHandler() throws Exception {
        verify(keyboardHandler).setProject(newProject);
    }
}
