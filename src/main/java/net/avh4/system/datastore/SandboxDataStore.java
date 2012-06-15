package net.avh4.system.datastore;

import net.avh4.sandbox.Sandbox;

public class SandboxDataStore extends FileDataStore {

    public SandboxDataStore() {
        super(new Sandbox().getRoot());
    }
}
