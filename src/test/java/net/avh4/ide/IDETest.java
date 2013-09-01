package net.avh4.ide;

import net.avh4.system.datastore.SandboxDataStore;
import org.junit.Test;

import static net.avh4.util.imagecomparison.hamcrest.ImageComparisonMatchers.isApproved;
import static org.hamcrest.MatcherAssert.assertThat;

public class IDETest {

    @Test
    public void testInitialState() throws Exception {
        final IDE subject = new IDE(new SandboxDataStore());
        assertThat(subject, isApproved());
    }
}
