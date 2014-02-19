package geogebra.html5.css;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface GuiResources extends ClientBundle {
	
	GuiResources INSTANCE = GWT.create(GuiResources.class);
	
	//@Source("geogebra/resources/images/ggb4-splash-h120.png")
	//ImageResource getGeoGebraWebSplash();
	
	@Source("geogebra/resources/images/spinner.gif")
	ImageResource getGeoGebraWebSpinner();
	
	@Source("geogebra/resources/images/nav_play.png")
	ImageResource navPlay();
	
	@Source("geogebra/resources/images/nav_pause.png")
	ImageResource navPause();
	
	@Source("geogebra/resources/images/view-refresh.png")
	ImageResource viewRefresh();
	
	@Source("geogebra/resources/images/spacer.png")
	ImageResource spacer();
	
	@Source("geogebra/resources/images/arrow_dockbar_triangle_down.png")
	ImageResource algebra_down();
	
	@Source("geogebra/resources/images/arrow_dockbar_triangle_left.png")
	ImageResource algebra_left();
	
	@Source("geogebra/resources/images/triangle-down.png")
	ImageResource triangle_down();

	@Source("geogebra/resources/images/splash-ggb4.svg")
	TextResource ggb4Splash();
	
	@Source("geogebra/resources/js/zipjs/dataview.js")
	TextResource dataViewJs();
	
	@Source("geogebra/resources/js/zipjs/zip-3.js")
	TextResource zipJs();
	
	@Source("geogebra/resources/js/zipjs/deflate.js")
	TextResource deflateJs();

	@Source("geogebra/resources/js/zipjs/inflate.js")
	TextResource inflateJs();
	
	@Source("geogebra/resources/js/zipjs/base64.js")
	TextResource base64Js();
	
	@Source("geogebra/resources/js/zipjs/arraybuffer.js")
	TextResource arrayBufferJs();

	
	@Source("geogebra/resources/css/mathquillggb.css")
	TextResource mathquillggbCss();
	
	@Source("geogebra/resources/js/jquery-1.7.2.min.js")
	TextResource jQueryJs();

	@Source("geogebra/resources/js/mathquillggb.js")
	TextResource mathquillggbJs();

	@Source("geogebra/resources/js/properties_keys_en.js")
	TextResource propertiesKeysJS();

	@Source("geogebra/resources/js/properties_keys_en_GB.js")
	TextResource propertiesKeysJSenGB();
	
	@Source("geogebra/resources/images/spinner.html")
	TextResource ggbSpinnerHtml();
	
	@Source("geogebra/resources/images/ggbSplash.html")
	TextResource ggbSplashHtml();

	@Source("geogebra/resources/css/clean-2.css")
	TextResource style();
}

