package net.avh4.ide;

import net.avh4.data.per2.Database;
import net.avh4.ide.platforms.CodeProject;
import net.avh4.test.junit.Nested;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@RunWith(Nested.class)
public class ProjectTemplatesTest {
    private ProjectTemplates subject;
    private CodeProject project;
    @Mock private Database db;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        subject = new ProjectTemplates();
    }

    public class AndroidHelloWorld {
        @Before
        public void setUp() throws Exception {
            project = subject.createAndroidHelloWorldProject(db);
        }
    }
}
