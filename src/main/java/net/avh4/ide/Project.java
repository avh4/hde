package net.avh4.ide;

import net.avh4.system.datastore.DataStore;

public class Project {

	private final String name;
	private final ProductBacklog backlog;

	public Project(final String name, final DataStore dataStore) {
		this.name = name;
		backlog = new ProductBacklog(dataStore);
	}

	public String getName() {
		return name;
	}

	public ProductBacklog getProductBacklog() {
		return backlog;
	}

}
