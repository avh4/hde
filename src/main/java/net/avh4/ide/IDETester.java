package net.avh4.ide;

import java.awt.Rectangle;

public class IDETester {

	final IDE ide;

	public IDETester(final IDE ide) {
		this.ide = ide;
	}

	public void type(final String text) {
		for (final char c : text.toCharArray()) {
			ide.type(c);
		}
	}

	public void pressEnter() {
		ide.type('\n');
	}

	public void clickOn(final String label) {
		clickOn(label, 0);
	}

	public void clickOn(final String label, final int index) {
		final Rectangle clickRegion = ide.findClickRegion(label, index);
		if (clickRegion == null) {
			throw new RuntimeException(String.format(
					"No click region found: %s, %d", label, index));
		}
		final int x = (int) Math.floor(clickRegion.x + clickRegion.width / 2.0);
		final int y = (int) Math
				.floor(clickRegion.y + clickRegion.height / 2.0);
		ide.click(x, y);
	}

	public void dragAndDrop(final String sourceLabel, final int sourceIndex,
			final String destinationLabel, final int destinationIndex) {

	}
}
