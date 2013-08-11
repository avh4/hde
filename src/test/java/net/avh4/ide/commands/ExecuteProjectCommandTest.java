package net.avh4.ide.commands;

import fj.data.List;
import net.avh4.ide.BuildResultsProvider;
import net.avh4.ide.platforms.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class ExecuteProjectCommandTest {
    private ExecuteProjectCommand subject;
    @Mock private PlatformBuilder builder;
    @Mock private CodeProject project;
    @Mock private CodeModule module;
    @Mock private BuildResults results;
    @Mock private PlatformExecutor executor;
    @Mock private BuildResultsProvider provider;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        stub(provider.get()).toReturn(results).toThrow(new RuntimeException("Only one BuildResults should be used"));
        stub(project.modules()).toReturn(List.list(module));

        subject = new ExecuteProjectCommand(builder, provider, executor);
        subject.execute(project);
    }

    @Test
    public void shouldBuildTheProject() throws Exception {
        verify(builder).build(module, results);
    }

    @Test
    public void shouldExecuteTheResults() throws Exception {
        verify(executor).execute(results);
    }
}
