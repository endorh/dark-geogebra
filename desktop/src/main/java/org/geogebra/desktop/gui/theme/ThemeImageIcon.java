package org.geogebra.desktop.gui.theme;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageProducer;

import javax.swing.ImageIcon;

import org.geogebra.desktop.util.ImageManagerD;

/**
 * Updatable ImageIcon. Instance from {@link ThemeD#icon}
 */
public class ThemeImageIcon extends ImageIcon {
	private static final Toolkit toolKit = Toolkit.getDefaultToolkit();
	private ImageProducer source;

	ThemeImageIcon(ImageProducer source) {
		this.source = source;
		themeUpdate();
	}

	@Override
	public void setImage(Image image) {
		super.setImage(ImageManagerD.addIconFilter(image));
	}

	public ImageProducer getSource() {
		return source;
	}

	public Image getUnfilteredImage() {
		return toolKit.createImage(source);
	}

	public void themeUpdate() {
		super.setImage(toolKit.createImage(ImageManagerD.addIconFilter(source)));
	}

	@Override
	public synchronized void paintIcon(Component c, Graphics g, int x, int y) {
		// Do not pass a JLabel component to drawImage to prevent NPE
		// See https://bugs.java.com/bugdatabase/view_bug.do?bug_id=4399407
		c.prepareImage(getImage(), null);
		g.drawImage(getImage(), x, y, null);
	}

	public static ImageProducer getSource(ImageIcon icon) {
		if (icon instanceof ThemeImageIcon) {
			return ((ThemeImageIcon) icon).getSource();
		}
		return icon.getImage().getSource();
	}

	public static Image getUnfilteredImage(ImageIcon icon) {
		if (icon instanceof ThemeImageIcon) {
			Image image = ((ThemeImageIcon) icon).getUnfilteredImage();
			return new ImageIcon(image).getImage();
		}
		return icon.getImage();
	}
}
