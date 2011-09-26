package net.avh4.ide;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import net.avh4.gui.View;
import net.avh4.system.datastore.DataStore;

import org.junit.Before;
import org.junit.Test;

public class IDETest {

	private IDE subject;

	@Before
	public void setUp() {
		final DataStore mockDataStore = mock(DataStore.class);
		subject = new IDE(mockDataStore);
	}

	@Test
	public void hasProject() {
		assertThat(subject.getProject(), notNullValue());
		assertThat(subject.getProject().getName(), is("New Project"));
	}

	@Test
	public void shouldHaveAProductBacklogView() {
		final View view = subject.getProductBacklogView();

		assertThat(view, not(nullValue()));
		assertThat(view.getPreferredSize().width, greaterThan(0));
		assertThat(view.getPreferredSize().height, greaterThan(0));
	}
}
