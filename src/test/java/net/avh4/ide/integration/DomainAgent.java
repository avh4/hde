package net.avh4.ide.integration;

import net.avh4.ide.ProjectTemplates;
import net.avh4.ide.commands.ExecuteProjectCommand;
import net.avh4.ide.platforms.CodeProject;
import net.avh4.ide.platforms.android.AndroidSdk;

import java.io.IOException;

public class DomainAgent implements Agent {
    private final ProjectTemplates projectTemplates;
    private final ExecuteProjectCommand executeProjectCommand;
    private final TestAndroidSdk androidSdk;
    private CodeProject project;

    public DomainAgent(ProjectTemplates projectTemplates, ExecuteProjectCommand executeProjectCommand, AndroidSdk androidSdk) {
        this.projectTemplates = projectTemplates;
        this.executeProjectCommand = executeProjectCommand;
        this.androidSdk = (TestAndroidSdk) androidSdk;
    }

    @Override
    public void createNewProject(String projectName) {
        project = projectTemplates.createAndroidHelloWorldProject();
    }

    @Override
    public boolean isEditorVisible() {
        // Not applicable
        return true;
    }

    @Override
    public String sourceFileContents(String sourceFileName) {
        return project.modules().head().classes().head().getSource();
    }

    @Override
    public void executeProject() {
        executeProjectCommand.execute(project);
    }

    @Override
    public void openProject(String path) {
        throw new RuntimeException("Not implemented");  // TODO
    }

    @Override
    public void assertApkWasExecuted(String expectedHelloWorldText) throws IOException {
        androidSdk.assertApkWasExecuted(expectedHelloWorldText);
    }

    @Override
    public void replaceEditorText(String searchString, String replacement) {
        throw new RuntimeException("Not implemented");  // TODO
    }
}
