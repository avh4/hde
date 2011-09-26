package net.avh4.ide;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FeatureTest {
	
	private Feature subject;

	@Before
	public void setUp() {
		subject = new Feature("Process booking of a flight");
	}
	
	@Test
	public void shouldKnowItsName() {
		assertThat(subject.getName(), is("Process booking of a flight"));
	}
	
	@Test
	public void shouldHaveNoEstimate() {
		assertThat(subject.getEstimate(), nullValue());
	}
	
	@Test
	public void canHaveAnEstimateSet() {
		assertThat(subject.getEstimate(), not(3));
		
		subject.setEstimate(3);
		
		assertThat(subject.getEstimate(), is(3));
	}
	
	@Test
	public void canHaveAnEstimateRemoved() {
		subject.setEstimate(3);
		assertThat(subject.getEstimate(), is(3));
		
		subject.setEstimate(null);
		
		assertThat(subject.getEstimate(), nullValue());
	}

}
