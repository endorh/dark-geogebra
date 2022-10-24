package org.geogebra.common.main.settings;

import java.util.LinkedList;

public class ControlsSettings extends AbstractSettings {
	public ControlsSettings(LinkedList<SettingListener> listeners) {
		super(listeners);
	}

	private boolean enableWarpTranslate = true;

	public ControlsSettings() {}

	public boolean isEnableWarpTranslate() {
		return enableWarpTranslate;
	}

	public void setEnableWarpTranslate(boolean enableWarpTranslate) {
		this.enableWarpTranslate = enableWarpTranslate;
	}
}
