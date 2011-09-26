package net.avh4.ide;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;

import net.avh4.gui.View;
import net.avh4.gui.swing.SwingViewFrame;
import net.avh4.system.datastore.DataStore;
import net.avh4.system.datastore.FileDataStore;

public class IDE implements View {

	private static final long serialVersionUID = 1L;

	public static void main(final String[] args) {
		final IDE ide = new IDE(new FileDataStore("."));
		final SwingViewFrame window = new SwingViewFrame(ide);
		window.setVisible(true);
	}

	private final Project project;
	private final ProductBacklogView productBacklogView;

	public IDE(final DataStore dataStore) {
		project = new Project("New Project", dataStore);
		productBacklogView = new ProductBacklogView(project.getProductBacklog());
	}

	public void userCommand(final String command, final String text) {
	}

	public void enterText(final String text) {
	}

	public Collection<BuildError> getErrors() {
		return new ArrayList<BuildError>();
	}

	public Collection<BuildWarning> getWarnings() {
		return new ArrayList<BuildWarning>();
	}

	public Project getProject() {
		return project;
	}

	public ProductBacklogView getProductBacklogView() {
		productBacklogView.setSize(productBacklogView.getPreferredSize());
		return productBacklogView;
	}

	public ExecutionResults runProject() {
		return new ExecutionResults();
	}

	@Override
	public void type(final char c) {
		productBacklogView.type(c);
	}

	@Override
	public Rectangle findClickRegion(final String label, final int index) {
		return productBacklogView.findClickRegion(label, index);
	}

	@Override
	public void click(final int x, final int y) {
		productBacklogView.click(x, y);
	}

	@Override
	public Dimension getPreferredSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setSize(final Dimension size) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(final Graphics2D g) {
		// TODO Auto-generated method stub

	}
}
