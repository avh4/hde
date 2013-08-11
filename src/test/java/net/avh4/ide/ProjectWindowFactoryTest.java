package net.avh4.ide;

import fj.data.List;
import net.avh4.ide.editing.EditorWindow;
import net.avh4.ide.editing.EditorWindowProvider;
import net.avh4.ide.platforms.CodeClass;
import net.avh4.ide.platforms.CodeModule;
import net.avh4.ide.platforms.CodeProject;
import net.avh4.test.junit.Nested;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.stub;

@RunWith(Nested.class)
public class ProjectWindowFactoryTest {
    private ProjectWindowFactory subject;
    @Mock private EditorWindowProvider provider;
    @Mock private EditorWindow editorWindow;
    @Mock private CodeProject project;
    @Mock private CodeModule module;
    @Mock private CodeClass codeClass;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(provider.get(codeClass)).toReturn(editorWindow);
        stub(project.modules()).toReturn(List.list(module));
        stub(module.classes()).toReturn(List.list(codeClass));
        subject = new ProjectWindowFactory(provider);
    }

    public class DefaultWindow {

        private Window window;

        @Before
        public void setUp() throws Exception {
            window = subject.getDefaultWindow(project);
        }

        @Test
        public void shouldCreateEditorWindowForMainClass() throws Exception {
            assertThat(window).isSameAs(editorWindow);
        }
    }
}
