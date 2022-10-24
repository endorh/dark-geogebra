package org.geogebra.common.geogebra3D.main;

import org.geogebra.common.geogebra3D.euclidian3D.openGL.Renderer;

/**
 * Class providing vertex shader
 *
 */
public class VertexShader {

	final private static String vertexHeaderDesktop =
			"""
					#if __VERSION__ >= 130 // GLSL 130+ uses in and out
					  #define attribute in // instead of attribute and varying\s
					  #define varying out  // used by OpenGL 3 core and later.\s
					#endif
					#ifdef GL_ES\s
					  precision mediump float; // Precision Qualifiers
					  precision mediump int; // GLSL ES section 4.5.2
					#endif
					""";

	final private static String inUniform =
			"""

					uniform mat4 matrix;
					uniform vec3 lightPosition;
					uniform vec4 eyePosition;
					uniform vec2 ambiantDiffuse;
					uniform int enableLight;
					uniform int culling;
					uniform vec4 color;
					uniform vec3 normal;
					uniform int labelRendering;
					uniform vec3 labelOrigin;
					uniform int layer;
					uniform int opaqueSurfaces;
					""";

	final private static String light =
			"""
					  if (enableLight == 1){// color with light
					    float factor = dot(n, lightPosition);
					    factor = float(culling) * factor;
					    factor = max(0.0, factor);
					    float ambiant = ambiantDiffuse[0];
					    float diffuse = ambiantDiffuse[1];
					    if (eyePosition[3] < 0.5){ // parallel projection
					      viewDirection = vec3(eyePosition);
					    }else{ // perspective projection
					      viewDirection = normalize(attribute_Position - vec3(eyePosition));
					    }
					    lightReflect = normalize(reflect(lightPosition, n));
					    varying_Color.rgb = (ambiant + diffuse * factor) * c.rgb;
					    varying_Color.a = c.a;
					  }else{ //no light
					    lightReflect = vec3(0.0,0.0,0.0);
					    varying_Color = c;
					  }
					""";
	
	final private static String depthToColorString =
			"""
					  float gray = gl_Position.z + 0.5;
					  varying_Color.r = gray;
					  varying_Color.g = gray;
					  varying_Color.b = gray;
					  coordTexture = attribute_Texture;
					""";

	final private static String shiny_packed =
			inUniform
					+ "\n"
					+ "attribute vec3 attribute_Position;\n"
					+ "attribute vec3 attribute_Normal;\n"
					+ "attribute vec4 attribute_Color;\n"
					+ "attribute vec2 attribute_Texture;\n"

					+ "\n"
					+ "varying vec4 varying_Color;\n"
					+ "varying vec2 coordTexture;\n"
					+ "varying vec3 realWorldCoords;\n"
					+ "varying vec3 viewDirection;\n"
					+ "varying vec3 lightReflect;\n"

					+ "\n"
					+ "const vec4 FAR_FAR_AWAY = vec4(0.0, 0.0, 2.0, 1.0); // z max is 1\n"
					+ "\nvoid main(void)\n" 
					+ "{\n"
					+ "  vec4 c;\n" 
					+ "  int att_layer = 0;\n"

					+ "\n"
					+ "  if (color[0] < 0.0){ // then use per-vertex-color\n"
					+ "    c = attribute_Color;\n"
					+ "    att_layer = int(c.a / "
					  + Renderer.LAYER_FACTOR_FOR_CODING + ".0);\n"
					+ "    c.a = c.a - " + Renderer.LAYER_FACTOR_FOR_CODING
					  + ".0 * float(att_layer);\n" 
					+ "    att_layer = att_layer"
					  + Renderer.LAYER_MIN_STRING_WITH_OP 
					  + ";\n"
					+ "  }else{ // use per-object-color\n"
					+ "    c = color;\n" 
					+ "  }\n"

					+ "\n"
					+ "  // discard when alpha < 0 (actually will be discarded in\n"
					+ "  // fragment shader)\n"
					+ "  if (opaqueSurfaces == 1 && c.a < 0.99) {\n"
					+ "    c.a = -1.0;\n" 
					+ "  }\n" 
					+ "  if (c.a < 0.0) {\n"
					+ "    varying_Color = c;\n"
					+ "    gl_Position = FAR_FAR_AWAY; // allows early Z test\n"
					+ "    return;\n" 
					+ "  }\n"
					
					+ "\n"
					+ "  gl_Position = matrix * vec4(attribute_Position, 1.0);\n"
					
					+ "\n"
					+ "  // set layer as z-shift \n"
					+ "  float fLayer = float(layer + att_layer);\n"
					+ "  vec3 n;\n"
					+ "  if (normal.x > 1.5){ // then use per-vertex normal\n"
					+ "    n = attribute_Normal;\n" 
					+ "  }else{\n"
					+ "    n = normal;\n" 
					+ "  }\n"
					+ "  float normalScreenZ = 0.0;\n"
					+ "  if (n.x > -1.5){ // otherwise there is no normal\n"
					+ "    vec4 normalScreen = matrix * vec4(n, 0.0);\n"
					+ "    normalScreen.w = 0.0;\n"
					+ "    normalScreen = normalize(normalScreen);\n"
					+ "    normalScreenZ = normalScreen.z;\n" 
					+ "  }\n"
					+ "  // shift z position to avoid z-fighting\n"
					+ "  // use layer value\n"
					+ "  // decrease when normal get orthogonal to screen;\n"
					+ "  // constant values set by checking different screen\n"
					+ "  // resolutions and different mobile devices\n"
					+ "  gl_Position.z = gl_Position.z"
					  + " - 0.0008 * (1.0 - 0.5 * abs(normalScreenZ)) * fLayer * gl_Position.w; \n"

					+ "\n"
					+ "  if (labelRendering == 1){ // use special origin for labels\n"
					+ "    realWorldCoords = labelOrigin;\n" 
					+ "  }else{\n"
					+ "    realWorldCoords = attribute_Position;\n"
					+ "  }\n"

					+ "\n"

					+ (Renderer.TEST_DRAW_DEPTH_TO_COLOR ? depthToColorString
							: light)

					+ "\n"
					+ "  coordTexture = attribute_Texture;\n" 
					+ "}";

	/**
	 * @param isHTML5
	 *            whether to skip the desktop prefix
	 * @return shiny shader
	 */
	final public static String getVertexShaderShiny(boolean isHTML5) {
		if (isHTML5) {
				return shiny_packed;
		}
		return vertexHeaderDesktop + shiny_packed;
	}

}
