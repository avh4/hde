package net.avh4.ide.platforms;

public interface PlatformExecutor extends PlatformBuilder {
    void execute(BuildResults buildResults);
}
