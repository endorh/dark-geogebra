package org.geogebra.desktop.geogebra3D.euclidian3D.opengl.desktop;


import org.geogebra.desktop.geogebra3D.euclidian3D.opengl.RendererJogl;

import com.jogamp.opengl.glu.GLU;

public interface JoglAndGluProvider {

	public RendererJogl getJogl();

	public GLU getGLU();
}
