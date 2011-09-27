package net.avh4.ide;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.hamcrest.MatcherAssert.assertThat;
import net.avh4.system.datastore.SandboxDataStore;

import org.junit.Test;

public class IDETest {

	@Test
	public void testInitialState() throws Exception {
		final IDE subject = new IDE(new SandboxDataStore());
		assertThat(subject, isApproved());
	}

}
