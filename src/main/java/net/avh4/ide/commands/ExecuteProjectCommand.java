package net.avh4.ide.commands;

import net.avh4.ide.BuildResultsProvider;
import net.avh4.ide.ProjectCommand;
import net.avh4.ide.platforms.*;

import static com.google.common.base.Preconditions.checkNotNull;

public class ExecuteProjectCommand implements ProjectCommand {
    private final PlatformBuilder builder;
    private BuildResultsProvider provider;
    private final PlatformExecutor executor;

    public ExecuteProjectCommand(PlatformBuilder builder, BuildResultsProvider provider, PlatformExecutor executor) {
        this.builder = checkNotNull(builder);
        this.provider = checkNotNull(provider);
        this.executor = checkNotNull(executor);
    }

    @Override
    public void execute(CodeProject project) {
        checkNotNull(project);
        BuildResults results = provider.get();
        for (CodeModule codeModule : project.modules()) {
            builder.build(codeModule, results);
        }
        executor.execute(results);
    }
}
