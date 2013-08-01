package net.avh4.ide.integration;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.fest.assertions.Assertions.assertThat;

public class AndroidProjectsIntegrationTest {

    Agent agent = new Agent();

    @Test
    public void testHelloWorld() throws Exception {
        agent.createNewProject("Hello World");

        assertThat(agent.isEditorVisible()).isTrue();
        assertThat(agent.editorContents()).isEqualTo(getFileContents("HelloWorld.java.example"));
    }

    private String getFileContents(String resourceName) throws IOException {
        String resourcePath = getClass().getSimpleName() + "." + resourceName;
        final InputStream is = getClass().getResourceAsStream(resourcePath);

        if (is == null)
            throw new RuntimeException("No such file on classpath: " + getClass().getPackage().getName().replace('.', '/') + "/" + resourcePath);
        return IOUtils.toString(is);
    }
}
