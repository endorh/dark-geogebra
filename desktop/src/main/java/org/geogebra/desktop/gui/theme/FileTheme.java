package org.geogebra.desktop.gui.theme;

import static java.lang.Integer.parseInt;

import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.HashMap;
import java.util.Properties;

public class FileTheme extends ThemeD {
	public final HashMap<String, Color> colors = new HashMap<>();
	public final Properties properties;

	public FileTheme(String name, InputStream is) {
		super(name, (ThemeD) null);
		properties = new Properties();
		try {
			properties.load(is);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public void init() {
		if (properties.containsKey("parent")) {
			try {
				// parentTheme = Theme.getTheme(properties.getProperty("parent"));
			} catch (UnknownThemeException ignored) {}
		}
		if (parentTheme == null) {
			parentTheme = BuiltInThemeD.DEFAULT;
		}
		if (properties.containsKey("lookAndFeel")) {
			lookAndFeel = null;
			lookAndFeelName = properties.getProperty("lookAndFeel");
		}
		if (properties.containsKey("font")) {
			// TODO
		}
	}

	protected Color getColor(String name) {
		return colors.computeIfAbsent(name, (n) -> readColor(properties.getProperty(n)));
	}

	public Color readColor(String color) {
		if (color.startsWith("#")) {
			return new Color(parseInt(color.substring(1), 16));
		}
		if (color.matches("[0-9]+(?:\\s*,\\s*[0-9]+){2,3}")) {
			String[] parts = color.split("\\s*,\\s*");
			if (parts.length == 3) {
				return new Color(parseInt(parts[0]), parseInt(parts[1]), parseInt(parts[2]));
			} else {
				return new Color(parseInt(parts[0]), parseInt(parts[1]), parseInt(parts[2]), parseInt(parts[3]));
			}
		}
		if (properties.containsKey(color)) {
			return getColor(color);
		}
		throw new IllegalArgumentException("Invalid color: " + color);
	}
}
