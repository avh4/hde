package net.avh4.system.datastore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileDataStore implements DataStore {

	protected final File root;

	public FileDataStore(final String root) {
		this.root = new File(root);
	}

	public FileDataStore(final File root) {
		this.root = root;
	}

	@Override
	public void writeData(final String path, final String data)
			throws DataStoreException {
		try {
			final File target = new File(root, path);
			final File dir = target.getParentFile();
			dir.mkdirs();
			final FileWriter fw = new FileWriter(new File(root, path));
			fw.write(data);
			fw.close();
		} catch (final IOException e) {
			throw new DataStoreException("Could not write data", e);
		}
	}

}
