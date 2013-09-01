package net.avh4.ide.integration.support;

import net.avh4.ide.HdeModel;
import net.avh4.ide.platforms.CodeClass;
import net.avh4.ide.platforms.android.AndroidSdk;
import net.avh4.ide.test.support.TestAndroidSdk;

import java.io.IOException;

public class DomainAgent implements Agent {
    private final TestAndroidSdk androidSdk;
    private final HdeModel model;

    public DomainAgent(AndroidSdk androidSdk, HdeModel model) {
        this.model = model;
        this.androidSdk = (TestAndroidSdk) androidSdk;
    }

    @Override
    public void createNewProject(String projectName) {
        model.createNewProject(projectName);
    }

    @Override
    public boolean isEditorVisible() {
        // Not applicable
        return true;
    }

    @Override
    public String sourceFileContents(String sourceFileName) {
        return model.project().modules()[0].classes()[0].source();
    }

    @Override
    public void executeProject() {
        model.executeProject();
    }

    @Override
    public void openProject(String path) {
        model.openProject(path);
    }

    @Override
    public void assertApkWasExecuted(String expectedHelloWorldText) throws IOException {
        androidSdk.assertApkWasExecuted(expectedHelloWorldText);
    }

    @Override
    public void replaceEditorText(String sourceFileName, String searchString, String replacement) {
        final CodeClass codeClass = model.project().modules()[0].classes()[0];
        final String source = codeClass.source();
        final String newSource = source.replace(searchString, replacement);
        model.replaceClassSource(sourceFileName, newSource);
    }
}
