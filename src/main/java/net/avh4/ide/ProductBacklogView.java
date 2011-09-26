package net.avh4.ide;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import net.avh4.gui.ClickRegionView;
import net.avh4.gui.TextField;
import net.avh4.gui.TextModel;

public class ProductBacklogView extends ClickRegionView implements TextField {

	private static final int ITEM_ROW_HEIGHT = 20;
	private static final long serialVersionUID = -1904911050696960744L;
	private final ProductBacklog backlog;
	private final TextModel textModel = new TextModel();
	private int storyPointsSelected = -1;

	public ProductBacklogView(final ProductBacklog backlog) {
		this.backlog = backlog;
		setClickRegionTable(31, 66, 58, 20);
	}

	@Override
	public void paint(final Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		final Font headingFont = new Font("Baskerville", Font.BOLD
				| Font.ITALIC, 16);
		final Font itemFont = new Font("Helvetica", Font.PLAIN, 14);
		final Font estimateFont = new Font("Helvetica Light", Font.ITALIC, 14);
		final Font noItemsFont = new Font("Helvetica", Font.ITALIC, 14);

		g.setColor(Color.BLACK);

		g.setFont(headingFont);
		g.drawString("Backlog", 34, 43);
		g.drawLine(34, 45, 90, 45);

		int y = 81;
		for (int i = 0; i < backlog.getNumberOfFeatures(); i++) {
			g.setFont(itemFont);
			g.setColor(Color.BLACK);
			g.drawString(backlog.getFeatureName(i), 89, y);

			final Integer featureEstimate = backlog.getFeatureEstimate(i);
			if (featureEstimate != null) {
				g.setFont(estimateFont);
				g.drawString(Integer.toString(featureEstimate), 63, y);
			} else {
				g.setFont(estimateFont);
				g.setColor(Color.DARK_GRAY);
				g.drawString("--", 63, y);
			}

			y += ITEM_ROW_HEIGHT;
		}

		if (backlog.getNumberOfFeatures() == 0) {
			g.setFont(noItemsFont);
			g.setColor(Color.GRAY);

			g.drawString("The product backlog is empty", 63, y);
		}
	}

	@Override
	public String getText() {
		return textModel.getText();
	}

	@Override
	public void type(final char c) {
		if (c == '\n') {
			if (storyPointsSelected != -1) {
				backlog.estimateFeature(
						backlog.getFeatureName(storyPointsSelected),
						Integer.parseInt(getText()));
			} else {
				backlog.createFeature(getText());
			}
			textModel.clear();
		} else {
			textModel.replaceSelection(Character.toString(c));
		}
	}

	@Override
	public void regionClick(final String label, final int index) {
		storyPointsSelected = index;
		textModel.clear();
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400, 400);
	}

	@Override
	public void setSize(final Dimension size) {
		// TODO Auto-generated method stub

	}
}
