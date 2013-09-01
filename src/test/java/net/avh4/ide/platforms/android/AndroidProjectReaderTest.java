package net.avh4.ide.platforms.android;

import net.avh4.data.per2.Database;
import net.avh4.data.per2.DatabaseImpl;
import net.avh4.ide.platforms.CodeProject;
import net.avh4.ide.test.support.TestDatabase;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class AndroidProjectReaderTest {
    private AndroidProjectReader subject;
    private CodeProject project;

    @Before
    public void setUp() throws Exception {
        subject = new AndroidProjectReader(new TestDatabase());
        project = subject.read("./examples/Tasks");
    }

    @Test
    public void shouldCreateAProject() throws Exception {
        assertThat(project).isNotNull();
    }

    @Test
    public void shouldHaveACodeModule() throws Exception {
        assertThat(project.modules()).isNotNull();
        assertThat(project.modules()[0]).isNotNull();
        assertThat(project.modules()[0].name()).isEqualTo("Tasks-main");
    }

    @Test
    public void shouldHaveATestModule() throws Exception {
        assertThat(project.modules()[1]).isNotNull();
        assertThat(project.modules()[1].name()).isEqualTo("Tasks-test");
    }

    @Test
    public void shouldHaveCodeModuleClasses() throws Exception {
        assertThat(project.modules()[0].classes()).hasSize(6);
    }

    @Test
    public void shouldGiveNamesToCodeModuleClasses() throws Exception {
        assertThat(project.modules()[0].classes()[0].name()).isEqualTo("Logger");
        assertThat(project.modules()[0].classes()[1].name()).isEqualTo("TaskListActivity");
        assertThat(project.modules()[0].classes()[2].name()).isEqualTo("TaskListAdapter");
        assertThat(project.modules()[0].classes()[3].name()).isEqualTo("TaskListFragment");
        assertThat(project.modules()[0].classes()[4].name()).isEqualTo("Task");
        assertThat(project.modules()[0].classes()[5].name()).isEqualTo("TimeUtil");
    }

    @Test
    public void shouldGiveSourceToCodeModuleClasses() throws Exception {
        assertThat(project.modules()[0].classes()[0].source())
                .startsWith("package ").contains("public class").endsWith("}\n");
    }

    @Test
    public void shouldGivePackageNameToCodeModuleClasses() throws Exception {
        assertThat(project.modules()[0].classes()[0].packageName()).isEqualTo("net.avh4.tasks.android");
        assertThat(project.modules()[0].classes()[1].packageName()).isEqualTo("net.avh4.tasks.android");
        assertThat(project.modules()[0].classes()[2].packageName()).isEqualTo("net.avh4.tasks.android");
        assertThat(project.modules()[0].classes()[3].packageName()).isEqualTo("net.avh4.tasks.android");
        assertThat(project.modules()[0].classes()[4].packageName()).isEqualTo("net.avh4.tasks");
        assertThat(project.modules()[0].classes()[5].packageName()).isEqualTo("net.avh4.tasks");
    }

    @Test
    public void shouldHaveTestModuleClasses() throws Exception {
        assertThat(project.modules()[1].classes()).hasSize(1);
    }
}
