package net.avh4.ide;

import net.avh4.system.datastore.DataStore;
import net.avh4.system.datastore.DataStoreException;

public class Feature {

	private final String name;
	private Integer estimate;

	public Feature(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Integer getEstimate() {
		return estimate;
	}

	public void setEstimate(final Integer estimate) {
		this.estimate = estimate;
	}

	public void save(final DataStore store) throws DataStoreException {
		final String safeName = name.toLowerCase().replaceAll(" ", "_");
		store.writeData("features/" + safeName + ".feature", "");
	}

}
