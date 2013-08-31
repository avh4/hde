package net.avh4.ide.integration;

import com.googlecode.dex2jar.reader.DexFileReader;
import com.googlecode.dex2jar.visitors.DexClassVisitor;
import com.googlecode.dex2jar.visitors.DexFileVisitor;
import fj.data.List;
import net.avh4.ide.integration.dex.BasicDexClassVisitor;
import net.avh4.ide.integration.dex.StringConstantCapturingCodeVisitor;
import net.avh4.ide.platforms.android.AndroidSdk;
import net.avh4.util.MultiMap;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import static org.fest.assertions.Assertions.assertThat;

public class TestAndroidSdk extends AndroidSdk {
    private final MultiMap<String, AdbCommand> adbCommands = new MultiMap<String, AdbCommand>();

    @Override
    public void execute(File projectRoot, String... command) {
        if (command[0].equals("adb")) {
            logAdbCommand(projectRoot, command);
        } else {
            super.execute(projectRoot, command);
        }
    }

    private void logAdbCommand(File projectRoot, String[] command) {
        adbCommands.add(command[1], new AdbCommand(projectRoot, command));
    }

    public List<AdbCommand> adbCommands(String type) {
        return adbCommands.get(type).valueE("No commands for adb " + type);
    }

    public void assertApkWasExecuted(String expectedHelloWorldText) throws IOException {
        final String installedApkPath = adbCommands("install").head().arg(1);
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

        assertThat(adbCommands("shell").head().args())
                .contains("am", "start", "com.example.Hello_World/.HelloWorldActivity");
    }

    public class AdbCommand {
        private final File projectRoot;
        private final String[] command;

        public AdbCommand(File projectRoot, String[] command) {
            this.projectRoot = projectRoot;
            this.command = command;
        }

        public String arg(int i) {
            return command[i + 2];
        }

        public Iterable<String> args() {
            return Arrays.asList(command).subList(2, command.length);
        }
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

    private static class DexFileReport {
        public ArrayList<String> classNames;
        public String lastConstantString;
    }
}
