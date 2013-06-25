package net.avh4.ide;

import net.avh4.framework.uilayer.Color;
import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.Font;
import net.avh4.framework.uilayer.scene.*;
import net.avh4.math.geometry.Rect;

public class TestResultsView implements Element {

    private static final Font FONT = new Font("Andika-R.ttf", 14);
    private static final int COLOR_PENDING = 0xff7f7f50;
    private static final int INDENT = 15;
    private static final int LINE = 20;

    public Scene getScene() {
        final Scene scene = new Scene("Test Results");

        scene.add(Rect.fromTopLeft(0, 0, 800, 600), new SceneRect(Color.WHITE));
        scene.add(Rect.fromTopLeft(10, 10, 500, 0), new SceneText("Features", FONT, Color.BLACK));
        scene.add(Rect.fromTopLeft(10 + INDENT, 10 + 1 * LINE, 500, 0),
                new SceneText("[PENDING] Adding a story without steps", FONT, COLOR_PENDING));
        scene.add(Rect.fromTopLeft(10 + INDENT, 10 + 2 * LINE, 500, 0),
                new SceneText("[PENDING] Immediately executing stories", FONT, COLOR_PENDING));

        return scene;
    }

    @Override
    public void draw(Rect rect, GraphicsOperations graphicsOperations, FontMetricsService fontMetricsService) {
        getScene().draw(rect, graphicsOperations, fontMetricsService);
    }
}
