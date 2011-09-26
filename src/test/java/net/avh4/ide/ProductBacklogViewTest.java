package net.avh4.ide;

import static net.avh4.util.imagecomparison.Matchers.isApproved;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import net.avh4.gui.View;
import net.avh4.gui.test.ComponentTestGuiSystem;
import net.avh4.gui.test.GuiSystem;

import org.junit.Before;
import org.junit.Test;

public class ProductBacklogViewTest {

	private ProductBacklog backlog;
	private View subject;
	private GuiSystem system;

	@Before
	public void setUp() {
		backlog = mock(ProductBacklog.class);
		subject = new ProductBacklogView(backlog);
		system = new ComponentTestGuiSystem(subject);
	}

	@Test
	public void normalBacklog() throws Exception {
		stub(backlog.getNumberOfFeatures()).toReturn(4);
		stub(backlog.getFeatureName(0)).toReturn("Adding numbers");
		stub(backlog.getFeatureName(1)).toReturn("Calculating a percentage");
		stub(backlog.getFeatureName(2)).toReturn("Subtracting numbers");
		stub(backlog.getFeatureName(3)).toReturn("Negating a number");
		stub(backlog.getFeatureEstimate(0)).toReturn(2);
		stub(backlog.getFeatureEstimate(1)).toReturn(2);
		stub(backlog.getFeatureEstimate(2)).toReturn(1);
		stub(backlog.getFeatureEstimate(3)).toReturn(1);

		assertThat(subject, isApproved());
	}

	@Test
	public void backlogWithNullEstimates() throws Exception {
		stub(backlog.getNumberOfFeatures()).toReturn(1);
		stub(backlog.getFeatureName(0)).toReturn("Adding numbers");
		stub(backlog.getFeatureEstimate(0)).toReturn(null);

		assertThat(subject, isApproved());
	}

	@Test
	public void emptyBacklog() throws Exception {
		stub(backlog.getNumberOfFeatures()).toReturn(0);

		assertThat(subject, isApproved());
	}

	@Test
	public void createFeature() {
		assertThat(system.componentHasFocus(subject), is(true));

		system.type("Multiplying numbers\n");

		verify(backlog).createFeature("Multiplying numbers");
	}

	@Test
	public void createFeatures() {
		assertThat(system.componentHasFocus(subject), is(true));

		system.type("Multiplying numbers\n");
		system.type("Subtracting numbers\n");

		verify(backlog).createFeature("Multiplying numbers");
		verify(backlog).createFeature("Subtracting numbers");
	}

	@Test
	public void testStoryPointClickRegions() throws Exception {
		stub(backlog.getNumberOfFeatures()).toReturn(1);
		stub(backlog.getFeatureName(0)).toReturn("Adding numbers");
		stub(backlog.getFeatureEstimate(0)).toReturn(null);

		assertThat(drawClickRegions(subject, "story points"), isApproved());
	}

	@Test
	public void testEstimateStoryPoints() throws Exception {
		stub(backlog.getNumberOfFeatures()).toReturn(1);
		stub(backlog.getFeatureName(0)).toReturn("Adding numbers");
		stub(backlog.getFeatureEstimate(0)).toReturn(null);

		subject.click(32, 67);
		subject.type('5');
		subject.type('\n');

		verify(backlog).estimateFeature("Adding numbers", 5);
	}

	@Test
	public void testEstimateStoryPoints2() throws Exception {
		stub(backlog.getNumberOfFeatures()).toReturn(2);
		stub(backlog.getFeatureName(0)).toReturn("Adding numbers");
		stub(backlog.getFeatureName(1)).toReturn("Subtracting numbers");
		stub(backlog.getFeatureEstimate(0)).toReturn(null);
		stub(backlog.getFeatureEstimate(1)).toReturn(null);

		subject.click(32, 67 + 20);
		subject.type('2');
		subject.type('\n');

		verify(backlog).estimateFeature("Subtracting numbers", 2);
	}

	private static BufferedImage drawClickRegions(final View subject,
			final String label) {
		final BufferedImage image = new BufferedImage(
				subject.getPreferredSize().width,
				subject.getPreferredSize().height, BufferedImage.TYPE_INT_ARGB);
		final Graphics2D g = image.createGraphics();

		// Draw the regions
		final Rectangle region = subject.findClickRegion(label, 0);
		if (region != null) {
			g.setColor(Color.MAGENTA);
			g.draw(region);
		}

		g.dispose();
		return image;
	}
}
