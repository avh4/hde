package net.avh4.system.datastore;

import java.io.IOException;

public class DataStoreException extends IOException {

	private static final long serialVersionUID = 389005993511057803L;

	public DataStoreException(final String message, final Throwable cause) {
		super(message, cause);
	}

}
