package net.avh4.ide;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import net.avh4.system.datastore.DataStore;

import org.junit.Before;
import org.junit.Test;

public class ProjectTest {

	private Project subject;

	@Before
	public void setUp() {
		final DataStore mockDataStore = mock(DataStore.class);
		subject = new Project("Asteroids", mockDataStore);
	}

	@Test
	public void shouldKnowItsName() {
		assertThat(subject.getName(), is("Asteroids"));
	}

	@Test
	public void shouldHaveAProductBacklog() {
		assertThat(subject.getProductBacklog(), notNullValue());
	}
}
