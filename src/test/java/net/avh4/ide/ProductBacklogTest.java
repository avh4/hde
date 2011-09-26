package net.avh4.ide;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import net.avh4.system.datastore.DataStore;

import org.junit.Before;
import org.junit.Test;

public class ProductBacklogTest {

	private ProductBacklog subject;

	@Before
	public void setUp() {
		final DataStore mockDataStore = mock(DataStore.class);
		subject = new ProductBacklog(mockDataStore);
	}

	@Test
	public void shouldStartEmpty() {
		assertThat(subject.getNumberOfFeatures(), is(0));
	}

	@Test
	public void shouldCreateNewFeatures() {
		subject.createFeature("Adding numbers");
		assertThat(subject.getNumberOfFeatures(), is(1));
	}

	@Test
	public void shouldRememberFeatureNames() {
		subject.createFeature("Adding numbers");
		assertThat(subject.getFeatureName(0), is("Adding numbers"));
	}

	@Test
	public void canReorderFeatures() {
		subject.createFeature("Adding numbers");
		subject.createFeature("Calculating percentages");
		assertThat(subject.getFeatureName(0), not("Calculating percentages"));

		subject.setPriority("Calculating percentages", 0);

		assertThat(subject.getFeatureName(0), is("Calculating percentages"));
	}

	@Test
	public void canEstimateFeatures() {
		subject.createFeature("Adding numbers");
		assertThat(subject.getFeatureEstimate(0), not(5));

		subject.estimateFeature("Adding numbers", 5);

		assertThat(subject.getFeatureEstimate(0), is(5));
	}

	@Test
	public void canAddMultipleFeatures() {
		subject.createFeature("Adding numbers");
		subject.createFeature("Subtracting numbers");

		assertThat(subject.getFeatureName(1), is("Subtracting numbers"));
	}

	@Test
	public void findFeatureByName() {
		subject.createFeature("Adding numbers");
		subject.createFeature("Subtracting numbers");

		assertThat(subject.findFeatureByName("Subtracting numbers"), is(1));
	}

	// Given a project "Calculator"
	// And the product backlog is empty
	// When I add an item to the product backlog "Adding numbers"
	// And I add an item to the product backlog "Subtracting numbers"
	// And I add an item to the product backlog "Negating a number"
	// And I add an item to the product backlog "Calculating a percentage"
	// And I estimate "Adding numbers" to be 2 story points
	// And I estimate "Subtracting numbers" to be 1 story point
	// And I estimate "Negating a number" to be 1 story point
	// And I estimate "Calculating a percentage" to be 2 story points
	// And I move "Adding numbers" to the top priority
	// And I move "Calculating a percentage" to the second priority

}
