package net.avh4.ide;

import net.avh4.ide.platforms.CodeProject;

public interface ProjectReader {
    CodeProject read(String path);
}
