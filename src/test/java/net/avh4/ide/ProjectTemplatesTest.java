package net.avh4.ide;

import net.avh4.data.per2.Database;
import net.avh4.data.per2.DatabaseImpl;
import net.avh4.data.per2.MemoryDatumStore;
import net.avh4.ide.platforms.CodeProject;
import net.avh4.test.junit.Nested;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(Nested.class)
public class ProjectTemplatesTest {
    private ProjectTemplates subject;
    private CodeProject project;
    private Database db;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        db = new DatabaseImpl(new MemoryDatumStore());
        subject = new ProjectTemplates();
    }

    public class AndroidHelloWorld {
        @Before
        public void setUp() throws Exception {
            project = subject.createAndroidHelloWorldProject(db);
        }

        @Test
        public void has_a_single_module() throws Exception {
            assertThat(project.modules().length).isEqualTo(1);
        }
    }
}
