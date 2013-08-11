package net.avh4.ide.integration;

import fj.data.List;
import net.avh4.ide.platforms.android.AndroidSdk;
import net.avh4.util.MultiMap;

import java.io.File;
import java.util.Arrays;

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
}
