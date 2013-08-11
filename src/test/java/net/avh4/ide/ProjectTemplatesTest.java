package net.avh4.ide;

import net.avh4.ide.platforms.CodeProject;
import net.avh4.test.junit.Nested;
import org.junit.Before;
import org.junit.runner.RunWith;

@RunWith(Nested.class)
public class ProjectTemplatesTest {
    private ProjectTemplates subject;
    private CodeProject project;

    @Before
    public void setUp() throws Exception {
        subject = new ProjectTemplates();
    }

    public class AndroidHelloWorld {
        @Before
        public void setUp() throws Exception {
            project = subject.createAndroidHelloWorldProject();
        }
    }
}
