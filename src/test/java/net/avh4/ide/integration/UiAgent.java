package net.avh4.ide.integration;

import com.googlecode.dex2jar.reader.DexFileReader;
import com.googlecode.dex2jar.visitors.*;
import net.avh4.ide.*;
import net.avh4.ide.editing.EditorWindow;
import net.avh4.ide.editing.EditorWindowView;
import net.avh4.ide.integration.dex.BasicDexClassVisitor;
import net.avh4.ide.integration.dex.StringConstantCapturingCodeVisitor;
import net.avh4.ide.platforms.android.AndroidSdk;
import net.avh4.ide.ui.WindowManager;
import org.mockito.MockitoAnnotations;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.fest.assertions.Assertions.assertThat;

public class UiAgent implements Agent {
    private final MutablePicoContainer pico = new DefaultPicoContainer();
    private final TestWindowManager windowManager;
    private final TestKeyboardInputSource keyboardInputSource;
    private final TestAndroidSdk androidSdk;

    public UiAgent() {
        MockitoAnnotations.initMocks(this);
        GlobalModule.configureContainer(pico);
        pico.removeComponent(AndroidSdk.class);
        pico.addComponent(new TestAndroidSdk());
        pico.addComponent(new TestWindowManager());
        pico.addComponent(new TestKeyboardInputSource());
        pico.addAdapter(TestWindowViewAdapter.create(EditorWindowView.class));
        pico.addAdapter(TestWindowViewAdapter.create(WelcomeWindowView.class));

        windowManager = validateTestComponent(TestWindowManager.class, WindowManager.class);
        keyboardInputSource = validateTestComponent(TestKeyboardInputSource.class, KeyboardInputSource.class);
        androidSdk = validateTestComponent(TestAndroidSdk.class, AndroidSdk.class);

        Application app = pico.getComponent(ApplicationProvider.class).get();
        app.start();
    }

    private <T> T validateTestComponent(Class<T> test, Class<? super T> real) {
        final T component = pico.getComponent(test);
        assertThat(component).isSameAs(pico.getComponent(real));
        return component;
    }

    @Override
    public void createNewProject(String projectName) {
        windowManager.find(WelcomeWindow.class).tapNewAndroidProject();
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
        windowManager.find(WelcomeWindow.class).tapOpenProject();
        windowManager.find(ProjectPicker.class).choose(path);
    }

    @Override
    public void assertApkWasExecuted(String expectedHelloWorldText) throws IOException {
        final String installedApkPath = androidSdk.adbCommands("install").head().arg(1);
        final File installedApk = new File(installedApkPath);
        assertThat(installedApk).exists();

        ZipFile zf = new ZipFile(installedApkPath);
        assertThat(zf.getEntry("AndroidManifest.xml")).isNotNull();
        assertThat(zf.getEntry("META-INF/MANIFEST.MF")).isNotNull();
        assertThat(zf.getEntry("META-INF/CERT.SF")).isNotNull();
        assertThat(zf.getEntry("META-INF/CERT.RSA")).isNotNull();

        final ZipEntry entry = zf.getEntry("classes.dex");
        final DexFileReport report = readDexFile(zf.getInputStream(entry));
        assertThat(report.classNames).contains("Lcom/example/Hello_World/HelloWorldActivity;");
        assertThat(report.lastConstantString).isEqualTo(expectedHelloWorldText);

        assertThat(androidSdk.adbCommands("shell").head().args())
                .contains("am", "start", "com.example.Hello_World/.HelloWorldActivity");
    }

    private DexFileReport readDexFile(InputStream inputStream) throws IOException {
        final ArrayList<String> classes = new ArrayList<String>();
        final StringConstantCapturingCodeVisitor codeVisitor = new StringConstantCapturingCodeVisitor();
        DexFileReader df = new DexFileReader(inputStream);
        df.accept(new DexFileVisitor() {
            @Override
            public DexClassVisitor visit(int access_flags, String className, String superClass,
                                         String[] interfaceNames) {
                classes.add(className);
                return new BasicDexClassVisitor(codeVisitor);
            }

            @Override
            public void visitEnd() {
            }
        });
        final DexFileReport report = new DexFileReport();
        report.classNames = classes;
        report.lastConstantString = codeVisitor.capturedValue;
        return report;
    }

    @Override
    public void replaceEditorText(String searchString, String replacement) {

    }

    private static class DexFileReport {
        public ArrayList<String> classNames;
        public String lastConstantString;
    }

}
