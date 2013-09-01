package net.avh4.ide.test.support;

import net.avh4.data.per2.DatabaseImpl;
import net.avh4.data.per2.MemoryDatumStore;

public class TestDatabase extends DatabaseImpl {
    public TestDatabase() {
        super(new MemoryDatumStore());
    }
}
