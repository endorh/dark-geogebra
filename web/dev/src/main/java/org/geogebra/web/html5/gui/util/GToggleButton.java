/*
 * Copyright 2007 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.geogebra.web.html5.gui.util;

import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.editor.client.adapters.TakesValueEditor;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.Image;

/**
 * A <code>ToggleButton</code> is a stylish stateful button which allows the
 * user to toggle between <code>up</code> and <code>down</code> states.
 * 
 * <p>
 * <img class='gallery' src='doc-files/ToggleButton.png'/>
 * </p>
 * 
 * <h3>CSS Style Rules</h3>
 * <ul class="css">
 * <li>.gwt-ToggleButton-up/down/up-hovering/down-hovering/up-disabled/down-
 * disabled {.html-face}</li>
 * </ul>
 * 
 * <p>
 * <h3>Example</h3> {@example com.google.gwt.examples.ToggleButtonExample}
 * </p>
 */
public class GToggleButton extends GCustomButton
		implements HasValue<Boolean>, IsEditor<LeafValueEditor<Boolean>> {
	private static String STYLENAME_DEFAULT = "gwt-ToggleButton";

	private LeafValueEditor<Boolean> editor;

	{
		setStyleName(STYLENAME_DEFAULT);
	}

	/**
	 * Constructor for <code>ToggleButton</code>.
	 */
	public GToggleButton() {
		super();
	}

	/**
	 * Constructor for <code>ToggleButton</code>.
	 * 
	 * @param upImage
	 *            image for the default(up) face of the button
	 * @param downImage
	 *            image for the down face of the button
	 */
	public GToggleButton(Image upImage, Image downImage) {
		super(upImage, downImage);
	}

	public HandlerRegistration addValueChangeHandler(
			ValueChangeHandler<Boolean> handler) {
		return addHandler(handler, ValueChangeEvent.getType());
	}

	public LeafValueEditor<Boolean> asEditor() {
		if (editor == null) {
			editor = TakesValueEditor.of(this);
		}
		return editor;
	}

	/**
	 * Determines whether this button is currently down.
	 * 
	 * @return <code>true</code> if the button is pressed, false otherwise. Will
	 *         not return null
	 */
	public Boolean getValue() {
		return isDown();
	}

	@Override
	public boolean isDown() {
		// Changes access to public.
		return super.isDown();
	}

	/**
	 * {@inheritDoc} Does not fire {@link ValueChangeEvent}. (If you want the
	 * event to fire, use {@link #setValue(Boolean, boolean)})
	 */
	@Override
	public void setDown(boolean down) {
		// Changes access to public.
		super.setDown(down);
	}

	/**
	 * Sets whether this button is down.
	 * 
	 * @param value
	 *            true to press the button, false otherwise; null value implies
	 *            false
	 */
	public void setValue(Boolean value) {
		setValue(value, false);
	}

	/**
	 * Sets whether this button is down, firing {@link ValueChangeEvent} if
	 * appropriate.
	 * 
	 * @param val
	 *            true to press the button, false otherwise; null value implies
	 *            false
	 * @param fireEvents
	 *            If true, and value has changed, fire a
	 *            {@link ValueChangeEvent}
	 */
	public void setValue(Boolean val, boolean fireEvents) {
		boolean value = val == null ? false : val.booleanValue();

		boolean oldValue = fireEvents ? isDown() : false;
		setDown(value);
		if (fireEvents) {
			ValueChangeEvent.fireIfNotEqual(this, oldValue, value);
		}
	}

	@Override
	protected void onClick() {
		toggleDown();
		super.onClick();
		ValueChangeEvent.fire(this, isDown());
	}
}
