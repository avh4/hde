package net.avh4.datastore;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;

import net.avh4.sandbox.Sandbox;
import net.avh4.system.datastore.DataStore;
import net.avh4.system.datastore.FileDataStore;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

public class FileDataStoreTest {

	private Sandbox sandbox;
	private DataStore subject;

	@Before
	public void setUp() {
		sandbox = new Sandbox();
		subject = new FileDataStore(sandbox.getRoot());
	}

	@Test
	public void shouldWriteFiles() throws IOException {
		subject.writeData("dataKey", "data string");

		// Verify
		final FileReader fr = sandbox.newFileReader("./dataKey");
		final StringWriter sw = new StringWriter();
		IOUtils.copy(fr, sw);
		assertThat(sw.toString(), is("data string"));
	}

	@Test
	public void shouldWriteFilesInSubdirectories() throws IOException {
		subject.writeData("subdir/dataKey", "data string 2");

		// Verify
		final FileReader fr = sandbox.newFileReader("./subdir/dataKey");
		final StringWriter sw = new StringWriter();
		IOUtils.copy(fr, sw);
		assertThat(sw.toString(), is("data string 2"));
	}

}
