package net.avh4.ide.platforms;

import net.avh4.ide.platforms.util.FileSystem;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class BuildResults {
    private final FileSystem fileSystem;

    public BuildResults() {
        fileSystem = new FileSystem();
    }

    public void add(String artifactType, File file) {
        File resultFile = fileSystem.file(artifactType);
        try {
            FileUtils.copyFile(file, resultFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File root() {
        return fileSystem.root();
    }

    public File artifact(String artifactType) {
        return fileSystem.file(artifactType);
    }

    public String artifactPath(String artifactType) {
        return artifact(artifactType).getAbsolutePath();
    }
}
