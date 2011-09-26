package net.avh4.ide;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import net.avh4.system.datastore.DataStore;
import net.avh4.system.datastore.DataStoreException;

public class ProductBacklog {

	private final List<Feature> features;
	private final Map<String, Feature> featuresByName;
	private final DataStore dataStore;

	public ProductBacklog(final DataStore dataStore) {
		features = new LinkedList<Feature>();
		featuresByName = new HashMap<String, Feature>();
		this.dataStore = dataStore;
	}

	public int getNumberOfFeatures() {
		return features.size();
	}

	public void createFeature(final String name) {
		final Feature feature = new Feature(name);
		features.add(feature);
		featuresByName.put(name, feature);
		try {
			feature.save(dataStore);
		} catch (final DataStoreException e) {
			e.printStackTrace();
		}
	}

	public String getFeatureName(final int index) {
		return features.get(index).getName();
	}

	public void setPriority(final String name, final int rank) {
		final Feature feature = featuresByName.get(name);
		features.remove(feature);
		features.add(rank, feature);
	}

	public Integer getFeatureEstimate(final int index) {
		final Feature feature = features.get(index);
		return feature.getEstimate();
	}

	public void estimateFeature(final String name, final Integer estimate) {
		final Feature feature = featuresByName.get(name);
		feature.setEstimate(estimate);
	}

	public int findFeatureByName(final String name) {
		for (int i = 0; i < features.size(); i++) {
			if (features.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

}
