package net.avh4.ide.platforms;

import net.avh4.ide.TestResources;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.fest.assertions.Assertions.assertThat;

public class BuildResultsTest {
    private BuildResults subject;

    @Before
    public void setUp() throws Exception {
        subject = new BuildResults();
    }

    @Test
    public void shouldHaveRoot() throws Exception {
        assertThat(subject.root().exists()).isTrue();
    }

    @Test
    public void shouldHaveEmptyRoot() throws Exception {
        assertThat(subject.root().list()).isEmpty();
    }

    @Test
    public void shouldStoreAddedFiles() throws Exception {
        subject.add("apk", TestResources.exampleFile("AndroidManifest.xml"));

        final File file = subject.artifact("apk");
        assertThat(file).exists();
        assertThat(subject.artifactPath("apk")).isEqualTo(file.getAbsolutePath());
        assertThat(FileUtils.readFileToString(file)).isEqualTo(TestResources.exampleContents("AndroidManifest.xml"));
    }
}
