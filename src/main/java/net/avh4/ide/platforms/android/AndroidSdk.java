package net.avh4.ide.platforms.android;

import com.google.common.collect.ImmutableList;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AndroidSdk {

    public static final String SDK_PATH = "/Users/aaron/android-sdk-macosx";

    public void execute(File projectRoot, String... command) {
        List<String> tools = ImmutableList.of("android");
        List<String> platformTools = ImmutableList.of("adb");
        if (tools.contains(command[0])) {
            command[0] = SDK_PATH + "/tools/" + command[0];
        }
        if (platformTools.contains(command[0])) {
            command[0] = SDK_PATH + "/platform-tools/" + command[0];
        }
        ProcessBuilder pb = new ProcessBuilder(command);
        pb.inheritIO();
        pb.directory(projectRoot);
        try {
            Process p = pb.start();
            int v = p.waitFor();
            if (v != 0) throw new RuntimeException("Got return code: " + v);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
