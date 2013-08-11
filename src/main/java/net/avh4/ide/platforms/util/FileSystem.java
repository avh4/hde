package net.avh4.ide.platforms.util;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileSystem {
    public static final String PREFIX = "hde-";
    private final File root;

    public FileSystem() {
        try {
            root = createTempDirectory();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void addFile(String path, String fileContents) {
        final File file = new File(root, path);
        try {
            FileUtils.write(file, fileContents);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File root() {
        return root;
    }

    public String rootPath() {
        return root.getAbsolutePath();
    }

    private static File createTempDirectory() throws IOException {
        final File temp;

        temp = File.createTempFile(PREFIX, Long.toString(System.nanoTime()));

        if (!(temp.delete())) {
            throw new IOException("Could not delete temp file: "
                    + temp.getAbsolutePath());
        }

        if (!(temp.mkdir())) {
            throw new IOException("Could not create temp directory: "
                    + temp.getAbsolutePath());
        }

        return (temp);
    }

    public File file(String path) {
        return new File(root, path);
    }
}
