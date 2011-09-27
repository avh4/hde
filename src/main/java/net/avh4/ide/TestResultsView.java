package net.avh4.ide;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.Scene;

public class TestResultsView implements SceneCreator {

	private static final String FONT = "Andika-R.ttf";
	private static final int COLOR_PENDING = 0xff7f7f50;
	private static final int INDENT = 15;
	private static final int LINE = 20;

	@Override
	public Scene getScene() {
		final Scene scene = UILayer.newScene("Test Results");

		scene.addRect(0, 0, 800, 600, Color.WHITE);
		scene.addText("Features", 10, 10, 500, FONT, 14, Color.BLACK);
		scene.addText("[PENDING] Adding a story without steps", 10 + INDENT,
				10 + 1 * LINE, 500, FONT, 14, COLOR_PENDING);
		scene.addText("[PENDING] Immediately executing stories", 10 + INDENT,
				10 + 2 * LINE, 500, FONT, 14, COLOR_PENDING);

		return scene;
	}

}
