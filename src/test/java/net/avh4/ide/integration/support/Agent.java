package net.avh4.ide.integration.support;

import java.io.IOException;

public interface Agent {
    void createNewProject(String projectName);

    @Deprecated
    boolean isEditorVisible();

    String sourceFileContents(String sourceFileName);

    void executeProject();

    void openProject(String path);

    void assertApkWasExecuted(String expectedHelloWorldText) throws IOException;

    void replaceEditorText(String sourceFileName, String searchString, String replacement);
}
