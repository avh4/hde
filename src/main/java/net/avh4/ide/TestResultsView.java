package net.avh4.ide;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.SceneRect;
import net.avh4.framework.uilayer.scene.SceneText;

public class TestResultsView implements SceneCreator {

    private static final String FONT = "Andika-R.ttf";
    private static final int COLOR_PENDING = 0xff7f7f50;
    private static final int INDENT = 15;
    private static final int LINE = 20;

    @Override
    public Scene getScene() {
        final Scene scene = new Scene("Test Results");

        scene.add(new SceneRect(0, 0, 800, 600, Color.WHITE));
        scene.add(new SceneText("Features", 10, 10, 500, FONT, 14, Color.BLACK));
        scene.add(new SceneText("[PENDING] Adding a story without steps", 10 + INDENT,
                10 + 1 * LINE, 500, FONT, 14, COLOR_PENDING));
        scene.add(new SceneText("[PENDING] Immediately executing stories", 10 + INDENT,
                10 + 2 * LINE, 500, FONT, 14, COLOR_PENDING));

        return scene;
    }

}
