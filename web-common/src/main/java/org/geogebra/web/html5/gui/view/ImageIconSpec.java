package org.geogebra.web.html5.gui.view;

import org.geogebra.web.html5.gui.util.NoDragImage;
import org.gwtproject.dom.client.Element;
import org.gwtproject.resources.client.ResourcePrototype;

public class ImageIconSpec implements IconSpec {
	private final ResourcePrototype image;

	public ImageIconSpec(ResourcePrototype image) {
		this.image = image;
	}

	public ResourcePrototype getImage() {
		return image;
	}

	@Override
	public Element toElement() {
		return new NoDragImage(getImage(), 24).getElement();
	}
}
