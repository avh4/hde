package net.avh4.ide.platforms.android;

import net.avh4.ide.platforms.*;
import net.avh4.ide.platforms.util.FileSystem;

public class AntAndroidPlatform implements PlatformBuilder, PlatformExecutor {
    private final FileSystem fileSystem;
    private final AndroidSdk androidSdk;

    public AntAndroidPlatform(FileSystem fileSystem, AndroidSdk androidSdk) {
        this.fileSystem = fileSystem;
        this.androidSdk = androidSdk;
    }

    @Override
    public void build(CodeModule codeModule, BuildResults buildResults) {
        final String androidTarget = "android-16";
        createFiles(codeModule);
        androidSdk.execute(fileSystem.root(), "android", "update", "project", "--path", ".", "--target", androidTarget);
        androidSdk.execute(fileSystem.root(), "ant", "debug");
        buildResults.add("apk", fileSystem.file("bin/HelloWorldActivity-debug-unaligned.apk"));
    }

    @Override
    public void execute(BuildResults buildResults) {
        final String mainActivity = "com.example.Hello_World/.HelloWorldActivity";
        androidSdk.execute(fileSystem.root(), "adb", "install", "-r", buildResults.artifactPath("apk"));
        androidSdk.execute(fileSystem.root(), "adb", "shell", "am", "start", mainActivity);
    }

    private void createFiles(CodeModule codeModule) {
        for (CodeClass codeClass : codeModule.classes()) {
            final String filename = sourceFileName("src", codeClass.packageName(), codeClass.name());
            fileSystem.addFile(filename, codeClass.source());
        }
        fileSystem.addFile("AndroidManifest.xml", "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "          package=\"com.example.Hello_World\"\n" +
                "          android:versionCode=\"1\"\n" +
                "          android:versionName=\"1.0\">\n" +
                "    <uses-sdk android:minSdkVersion=\"10\"/>\n" +
                "    <application android:label=\"Hello World\">\n" +
                "        <activity android:name=\"HelloWorldActivity\"\n" +
                "                  android:label=\"Hello World\">\n" +
                "            <intent-filter>\n" +
                "                <action android:name=\"android.intent.action.MAIN\"/>\n" +
                "                <category android:name=\"android.intent.category.LAUNCHER\"/>\n" +
                "            </intent-filter>\n" +
                "        </activity>\n" +
                "    </application>\n" +
                "</manifest> \n");
    }

    private String sourceFileName(String prefix, String packageName, String className) {
        return prefix + "/"
                + packageName.replace(".", "/") + "/"
                + className + ".java";
    }
}
