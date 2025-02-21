package org.geogebra.common.kernel.geos.description;

import static org.geogebra.common.kernel.kernelND.GeoElementND.LABEL_CAPTION;
import static org.geogebra.common.kernel.kernelND.GeoElementND.LABEL_CAPTION_VALUE;
import static org.geogebra.common.kernel.kernelND.GeoElementND.LABEL_NAME_VALUE;
import static org.geogebra.common.kernel.kernelND.GeoElementND.LABEL_VALUE;

import org.geogebra.common.kernel.StringTemplate;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.LabelManager;
import org.geogebra.common.util.ToStringConverter;

/**
 * Default label description implementation for GeoElement.
 */
public class DefaultLabelDescriptionConverter implements ToStringConverter<GeoElement> {

	@Override
	public String convert(GeoElement element) {
		String labelDescription = switch (element.getLabelMode()) {
			case LABEL_CAPTION_VALUE -> getCaptionAndValue(element);
			case LABEL_NAME_VALUE -> element.getAlgebraDescriptionDefault();
			case LABEL_VALUE -> element.toDefinedValueString(element.getLabelStringTemplate());
			case LABEL_CAPTION -> element.getCaption(element.getLabelStringTemplate());
			default -> // case LABEL_NAME:
					element.getLabel(element.getLabelStringTemplate());
		};
		return labelDescription.startsWith(LabelManager.HIDDEN_PREFIX) ? "" : labelDescription;
	}

	private String getCaptionAndValue(GeoElement element) {
		if ("".equals(element.getRawCaption())) {
			return element.getAlgebraDescriptionDefault();
		}

		String retVal = element.getRawCaption();
		retVal += element.getLabelDelimiterWithSpace(StringTemplate.defaultTemplate);
		retVal += element.toValueString(StringTemplate.defaultTemplate);
		return retVal;
	}
}
