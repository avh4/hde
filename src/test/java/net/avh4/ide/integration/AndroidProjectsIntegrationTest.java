package net.avh4.ide.integration;

import com.google.common.collect.ImmutableList;
import net.avh4.ide.integration.support.Agent;
import net.avh4.ide.integration.support.IntegrationTestDomainModule;
import net.avh4.ide.integration.support.IntegrationTestModule;
import net.avh4.ide.integration.support.IntegrationTestUiModule;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.MockitoAnnotations;
import org.picocontainer.PicoContainer;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static com.google.common.base.Preconditions.checkNotNull;
import static net.avh4.ide.TestResources.exampleContents;
import static org.fest.assertions.Assertions.assertThat;

@RunWith(Parameterized.class)
public class AndroidProjectsIntegrationTest {
    @Parameterized.Parameters(name = "{0}")
    public static Collection<Object[]> data() {
        return ImmutableList.of(
                new Object[]{"domain", IntegrationTestDomainModule.class},
                new Object[]{"ui", IntegrationTestUiModule.class}
        );
    }

    final Agent agent;

    public AndroidProjectsIntegrationTest(@SuppressWarnings("UnusedParameters") String name,
                                          Class<? extends IntegrationTestModule> moduleClass) throws Exception {
        IntegrationTestModule module = moduleClass.newInstance();
        final PicoContainer pico = module.getContainer();
        this.agent = pico.getComponent(Agent.class);
        checkNotNull(this.agent, "The DI container did not provide an Agent");
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testNewAndroidProject() throws Exception {
        agent.createNewProject("Hello World");

        assertThat(agent.isEditorVisible()).isTrue();
        assertThat(agent.sourceFileContents("HelloWorldActivity")).isEqualTo(exampleContents("src/com/example/Hello_World/HelloWorldActivity.java"));

        agent.executeProject();

        agent.assertApkWasExecuted("Hello World");
    }

    @Test
    public void testEditedAndroidProject() throws Exception {
        agent.createNewProject("Hello World");

        assertThat(agent.isEditorVisible()).isTrue();
        agent.replaceEditorText("HelloWorldActivity", "Hello World", "Hello Moon");
        assertThat(agent.sourceFileContents("HelloWorldActivity")).isEqualTo(
                exampleContents("src/com/example/Hello_World/HelloWorldActivity.java")
                        .replace("\"Hello World\"", "\"Hello Moon\"")
        );

        agent.executeProject();

        agent.assertApkWasExecuted("Hello Moon");
    }

    @Test @Ignore
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
