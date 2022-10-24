/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kitfox.svg.xml;

import java.util.Objects;

/**
 *
 * @author kitfox
 */
public class StyleSheetRule {
	final String styleName;
	final String tag;
	final String className;

	public StyleSheetRule(String styleName, String tag, String className) {
		this.styleName = styleName;
		this.tag = tag;
		this.className = className;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 13 * hash
				+ (this.styleName != null ? this.styleName.hashCode() : 0);
		hash = 13 * hash + (this.tag != null ? this.tag.hashCode() : 0);
		hash = 13 * hash
				+ (this.className != null ? this.className.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final StyleSheetRule other = (StyleSheetRule) obj;
		if (!Objects.equals(this.styleName, other.styleName)) {
			return false;
		}
		if (!Objects.equals(this.tag, other.tag)) {
			return false;
		}
		if (!Objects.equals(this.className, other.className)) {
			return false;
		}
		return true;
	}

}
