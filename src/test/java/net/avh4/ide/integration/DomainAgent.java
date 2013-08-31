package net.avh4.ide.integration;

import java.io.IOException;

public class DomainAgent implements Agent {
    @Override
    public void createNewProject(String projectName) {
        throw new RuntimeException("Not implemented");  // TODO
    }

    @Override
    public boolean isEditorVisible() {
        throw new RuntimeException("Not implemented");  // TODO
    }

    @Override
    public String sourceFileContents(String sourceFileName) {
        throw new RuntimeException("Not implemented");  // TODO
    }

    @Override
    public void executeProject() {
        throw new RuntimeException("Not implemented");  // TODO
    }

    @Override
    public void openProject(String path) {
        throw new RuntimeException("Not implemented");  // TODO
    }

    @Override
    public void assertApkWasExecuted(String expectedHelloWorldText) throws IOException {
        throw new RuntimeException("Not implemented");  // TODO
    }

    @Override
    public void replaceEditorText(String searchString, String replacement) {
        throw new RuntimeException("Not implemented");  // TODO
    }
}
