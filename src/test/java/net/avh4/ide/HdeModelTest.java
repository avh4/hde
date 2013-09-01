package net.avh4.ide;

import net.avh4.data.per2.Database;
import net.avh4.ide.platforms.CodeProject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.stub;

public class HdeModelTest {
    private HdeModel subject;
    @Mock private Database db;
    @Mock private ProjectTemplates projectTemplates;
    @Mock private ProjectCommand executeProjectCommand;
    @Mock private ProjectReader projectReader;
    @Mock private CodeProject openedProject;
    @Mock private CodeProject newProject;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        stub(projectReader.read("/path/to/project")).toReturn(openedProject);
        stub(projectTemplates.createAndroidHelloWorldProject(db)).toReturn(newProject);
        subject = new HdeModel(db, projectTemplates, executeProjectCommand, projectReader);
    }

    @Test
    public void createProject_shouldSetTheCurrentProject() throws Exception {
        subject.createNewProject("Happy");
        assertThat(subject.project()).isSameAs(newProject);
    }

    @Test
    public void openProject_shouldSetTheCurrentProject() throws Exception {
        subject.openProject("/path/to/project");
        assertThat(subject.project()).isSameAs(openedProject);
    }
}
