package net.avh4.ide;

import net.avh4.framework.uilayer.Element;
import net.avh4.framework.uilayer.TimerUpdate;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.FontMetricsService;
import net.avh4.framework.uilayer.scene.GraphicsOperations;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.framework.uilayer.scene.ScenePlaceholder;
import net.avh4.math.geometry.Point;
import net.avh4.math.geometry.Rect;
import net.avh4.system.datastore.DataStore;
import net.avh4.system.datastore.DataStoreException;
import net.avh4.system.datastore.FileDataStore;

import java.io.File;

public class IDE implements UI {

    public static void main(String[] args) {
        UILayer.main(new IDE(new File(".")));
    }

    public IDE(final File root) {
        this(new FileDataStore(root));
    }

    public IDE(final DataStore dataStore) {
        try {
            dataStore.writeData("src/features/stories/features.feature",
                    "Story: Features");
        } catch (final DataStoreException e) {
            e.printStackTrace();
        }
    }

    private Scene getScene() {
        final Scene scene = new Scene("IDE");
        scene.add(Rect.fromTopLeft(0, 0, 100, 25), new ScenePlaceholder("New Story"));
        scene.add(Rect.fromTopLeft(100, 0, 100, 25), new ScenePlaceholder("New Scenario"));
        scene.add(Rect.fromTopLeft(200, 0, 100, 25), new ScenePlaceholder("Run Integration Tests"));
        return scene;
    }

    @Override
    public void draw(Rect rect, GraphicsOperations graphicsOperations, FontMetricsService fontMetricsService) {
        getScene().draw(rect, graphicsOperations, fontMetricsService);
    }

    @Override
    public void click(Rect bounds, Point p) {
    }

    @Override
    public void move(Rect bounds, Point p) {
    }

    @Override
    public void key(int i, boolean b) {
    }

    public Feature getSelectedFeature() {
        return new Feature();
    }

    public void type(final String string) {
        // TODO Auto-generated method stub
    }

    public Element getTestResultsView() {
        return new TestResultsView();
    }

    @Override
    public UpdateAction time() {
        return UpdateAction.NO_UPDATE;
    }
}
