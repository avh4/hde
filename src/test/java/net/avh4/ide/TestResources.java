package net.avh4.ide;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class TestResources {
    public static String exampleContents(String resourceName) throws IOException {
        final File file = exampleFile(resourceName);
        return FileUtils.readFileToString(file);
    }

    public static File exampleFile(String resourceName) {
        String root = "./examples/Hello World";
        String resourcePath = root + "/" + resourceName;

        return new File(resourcePath);
    }
}
