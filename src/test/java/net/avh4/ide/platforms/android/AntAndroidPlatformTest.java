package net.avh4.ide.platforms.android;

import net.avh4.data.per2.Database;
import net.avh4.data.per2.DatabaseImpl;
import net.avh4.data.per2.MemoryDatumStore;
import net.avh4.ide.ProjectTemplates;
import net.avh4.ide.platforms.BuildResults;
import net.avh4.ide.platforms.CodeModule;
import net.avh4.ide.platforms.util.FileSystem;
import org.fest.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.IOException;

import static net.avh4.ide.TestResources.exampleContents;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

public class AntAndroidPlatformTest {

    private AntAndroidPlatform subject;
    private CodeModule codeModule;
    @Mock private FileSystem fileSystem;
    @Mock private AndroidSdk androidSdk;
    @Mock private BuildResults buildResults;
    @Mock private File apkFile;
    @Mock private File buildResultsRoot;
    @Mock private File tmpRoot;
    private String buildResultsPath = "/my/home/project/target/2013-08-11-1311";
    private String apkPath = buildResultsPath + "/build.apk";

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        stub(fileSystem.root()).toReturn(tmpRoot);
        stub(fileSystem.file("bin/HelloWorldActivity-debug-unaligned.apk")).toReturn(apkFile);

        stub(buildResults.root()).toReturn(buildResultsRoot);
        stub(buildResults.artifactPath("apk")).toReturn(apkPath);

        Database db = new DatabaseImpl(new MemoryDatumStore());
        codeModule =new ProjectTemplates().createAndroidHelloWorldProject(db).modules()[0];

        subject = new AntAndroidPlatform(fileSystem, androidSdk);
    }

    @Test
    public void build_shouldProduceApk() throws Exception {
        subject.build(codeModule, buildResults);

        verifyFile("src/com/example/Hello_World/HelloWorldActivity.java");
        verifyFile("AndroidManifest.xml");

        InOrder order = Mockito.inOrder(androidSdk, buildResults);
        order.verify(androidSdk).execute(tmpRoot, "android", "update", "project", "--path", ".", "--target", "android-16");
        order.verify(androidSdk).execute(tmpRoot, "ant", "debug");

        order.verify(buildResults).add("apk", apkFile);
    }

    @Test
    public void execute_shouldInstallAndRunApk() throws Exception {
        subject.execute(buildResults);

        InOrder order = Mockito.inOrder(androidSdk);
        order.verify(androidSdk).execute(tmpRoot, "adb", "install", "-r", apkPath);
        order.verify(androidSdk).execute(tmpRoot, "adb", "shell", "am", "start", "com.example.Hello_World/.HelloWorldActivity");
    }

    private void verifyFile(String path) throws IOException {
        verify(fileSystem).addFile(path, exampleContents(path));
    }
}
