package net.avh4.ide;

import java.io.File;

import net.avh4.framework.uilayer.SceneCreator;
import net.avh4.framework.uilayer.UI;
import net.avh4.framework.uilayer.UILayer;
import net.avh4.framework.uilayer.scene.Scene;
import net.avh4.system.datastore.DataStore;
import net.avh4.system.datastore.DataStoreException;
import net.avh4.system.datastore.FileDataStore;

public class IDE implements UI {

	private final DataStore dataStore;

	public IDE(final File root) {
		this(new FileDataStore(root));
	}

	public IDE(final DataStore dataStore) {
		this.dataStore = dataStore;
		try {
			dataStore.writeData("src/features/stories/features.story",
					"Story: Features");
		} catch (final DataStoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Scene getScene() {
		final Scene scene = UILayer.newScene("IDE");
		scene.addPlaceholder("New Story", 0, 0, 100, 25);
		scene.addPlaceholder("New Scenario", 100, 0, 100, 25);
		scene.addPlaceholder("Run Integration Tests", 200, 0, 100, 25);
		return scene;
	}

	@Override
	public void click(final int x, final int y) {
		// TODO Auto-generated method stub

	}

	@Override
	public void key(final int keyCode) {
		// TODO Auto-generated method stub

	}

	public Feature getSelectedFeature() {
		return new Feature();
	}

	public void type(final String string) {
		// TODO Auto-generated method stub

	}

	public SceneCreator getTestResultsView() {
		return new TestResultsView();
	}

}
