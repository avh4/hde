package net.avh4.ide.integration.support;

import net.avh4.ide.*;
import net.avh4.ide.editing.EditorWindow;
import net.avh4.ide.platforms.android.AndroidSdk;
import net.avh4.ide.test.support.TestAndroidSdk;
import net.avh4.ide.test.support.TestKeyboardInputSource;
import net.avh4.ide.test.support.TestWindowManager;
import net.avh4.ide.ui.WindowManager;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import static net.avh4.ide.test.support.TestWindowViewAdapter.TestWindowView;
import static org.fest.assertions.Assertions.assertThat;

public class UiAgent implements Agent {
    private final TestWindowManager windowManager;
    private final TestKeyboardInputSource keyboardInputSource;
    private final TestAndroidSdk androidSdk;

    public UiAgent(WindowManager windowManager, KeyboardInputSource keyboardInputSource, AndroidSdk androidSdk,
                   ApplicationProvider applicationProvider) {
        MockitoAnnotations.initMocks(this);

        this.windowManager = validateTestComponent(TestWindowManager.class, windowManager);
        this.keyboardInputSource = validateTestComponent(TestKeyboardInputSource.class, keyboardInputSource);
        this.androidSdk = validateTestComponent(TestAndroidSdk.class, androidSdk);

        Application app = applicationProvider.get();
        app.start();
    }

    private <C, T extends C> T validateTestComponent(Class<T> test, C component) {
        assertThat(component).isInstanceOf(test);
        //noinspection unchecked
        return (T) component;
    }

    @Override
    public void createNewProject(String projectName) {
        windowManager.find(WelcomeWindow.class).actions().tapNewAndroidProject();
    }

    @Override
    public boolean isEditorVisible() {
        Window w = windowManager.visibleWindow();
        if (w == null) throw new RuntimeException("No visible windows");
        return w.functions().contains(Function.EDITING);
    }

    @Override
    public String sourceFileContents(String sourceFileName) {
        Window w = windowManager.visibleWindow();
        if (!(w instanceof EditorWindow))
            throw new RuntimeException("Don't know how to find the Editor window (if there is one)");
        EditorWindow e = (EditorWindow) w;
        return e.getContents();
    }

    @Override
    public void executeProject() {
        keyboardInputSource.sendKeyStroke("⇧⌘⑩");
    }

    @Override
    public void openProject(String path) {
        windowManager.find(WelcomeWindow.class).actions().tapOpenProject();
        windowManager.find(ProjectPicker.class).actions().choose(path);
    }

    @Override
    public void assertApkWasExecuted(String expectedHelloWorldText) throws IOException {
        androidSdk.assertApkWasExecuted(expectedHelloWorldText);
    }

    @Override
    public void replaceEditorText(String sourceFileName, String searchString, String replacement) {
        // TODO make sure we're looking at the right file
        final EditorWindow window = windowManager.find(EditorWindow.class);
        TestWindowView v = (TestWindowView) window.view();
        final String source = (String) v._get("setContent", 1);

        final String newSource = source.replace(searchString, replacement);

        window.actions().changeSourceText(newSource);
    }
}
