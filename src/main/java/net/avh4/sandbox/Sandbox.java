package net.avh4.sandbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Sandbox {

	private File root;

	public Sandbox() {
		try {
			root = createTempDirectory();
			root.deleteOnExit();
		} catch (final IOException e) {
			throw new RuntimeException("Unable to create sandbox", e);
		}
	}

	private static File createTempDirectory() throws IOException {
		final File temp;

		temp = File.createTempFile("temp", Long.toString(System.nanoTime()));

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

	public FileReader newFileReader(final String path)
			throws FileNotFoundException {
		return new FileReader(newFile(path));
	}

	public File newFile(final String path) {
		return new File(root, path);
	}

	public FileWriter newFileWriter(final String path) throws IOException {
		return new FileWriter(newFile(path));
	}

	public File getRoot() {
		return root;
	}

}
