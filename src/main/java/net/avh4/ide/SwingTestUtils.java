package net.avh4.ide;

import java.awt.AWTEvent;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.Date;

public class SwingTestUtils {

	public static void clickAt(final Component component) {
		clickAt(component, component.getBounds());
	}

	public static void clickAt(final Component component,
			final Rectangle clickRegion) {
		if (clickRegion == null) {
			throw new IllegalArgumentException("clickRegion may not be null");
		}
		final int x = (int) Math.floor(clickRegion.x + clickRegion.width / 2.0);
		final int y = (int) Math
				.floor(clickRegion.y + clickRegion.height / 2.0);

		final AWTEvent e = new MouseEvent(component, MouseEvent.MOUSE_PRESSED,
				new Date().getTime(), 0, x, y, 1, false);
		component.dispatchEvent(e);

	}

}
