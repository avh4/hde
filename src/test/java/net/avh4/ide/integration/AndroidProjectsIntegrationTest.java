package net.avh4.ide.integration;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;

import static net.avh4.ide.TestResources.exampleContents;
import static org.fest.assertions.Assertions.assertThat;

public class AndroidProjectsIntegrationTest {

    Agent agent;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        agent = new Agent();
    }

    @Test
    public void testNewAndroidProject() throws Exception {
        agent.createNewProject("Hello World");

        assertThat(agent.isEditorVisible()).isTrue();
        assertThat(agent.editorContents()).isEqualTo(exampleContents("src/com/example/Hello_World/HelloWorldActivity.java"));

        agent.executeProject();

        agent.assertApkWasExecuted();
    }

    @Test
    public void testOpenAndroidProject() throws Exception {
        agent.openProject("./examples/Tasks");

        assertThat(agent.isEditorVisible()).isTrue();

        agent.executeProject();
    }

    private String getFileContents(String resourceName) throws IOException {
        String resourcePath = getClass().getSimpleName() + "." + resourceName;
        final InputStream is = getClass().getResourceAsStream(resourcePath);

        if (is == null)
            throw new RuntimeException("No such file on classpath: " + getClass().getPackage().getName().replace('.', '/') + "/" + resourcePath);
        return IOUtils.toString(is);
    }
}
