// Copyright 2001, FreeHEP.
package org.freehep.graphicsio.emf.gdi;

import java.awt.Rectangle;
import java.io.IOException;

import org.freehep.graphicsio.emf.EMFConstants;
import org.freehep.graphicsio.emf.EMFInputStream;
import org.freehep.graphicsio.emf.EMFOutputStream;
import org.freehep.graphicsio.emf.EMFTag;

/**
 * GradientFill TAG.
 * 
 * @author Mark Donszelmann
 * @version $Id: GradientFill.java,v 1.4 2009-08-17 21:44:44 murkle Exp $
 */
public class GradientFill extends EMFTag implements EMFConstants {

	private Rectangle bounds;

	private int mode;

	private TriVertex[] vertices;

	private Gradient[] gradients;

	public GradientFill() {
		super(118, 1);
	}

	public GradientFill(Rectangle bounds, int mode, TriVertex[] vertices,
			Gradient[] gradients) {
		this();
		this.bounds = bounds;
		this.mode = mode;
		this.vertices = vertices;
		this.gradients = gradients;
	}

	@Override
	public EMFTag read(int tagID, EMFInputStream emf, int len)
			throws IOException {

		Rectangle bounds = emf.readRECTL();
		TriVertex[] vertices = new TriVertex[emf.readDWORD()];
		Gradient[] gradients = new Gradient[emf.readDWORD()];
		int mode = emf.readULONG();

		for (int i = 0; i < vertices.length; i++) {
			vertices[i] = new TriVertex(emf);
		}
		for (int i = 0; i < gradients.length; i++) {
			if (mode == GRADIENT_FILL_TRIANGLE) {
				gradients[i] = new GradientTriangle(emf);
			} else {
				gradients[i] = new GradientRectangle(emf);
			}
		}
		GradientFill tag = new GradientFill(bounds, mode, vertices, gradients);
		return tag;
	}

	@Override
	public void write(int tagID, EMFOutputStream emf) throws IOException {
		emf.writeRECTL(bounds);
		emf.writeDWORD(vertices.length);
		emf.writeDWORD(gradients.length);
		emf.writeULONG(mode);
		for (TriVertex vertex : vertices) {
			vertex.write(emf);
		}
		for (Gradient gradient : gradients) {
			gradient.write(emf);
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(super.toString()).append("\n");
		s.append("  bounds: ").append(bounds).append("\n");
		s.append("  mode: ").append(mode).append("\n");
		for (int i = 0; i < vertices.length; i++) {
			s.append("  vertex[").append(i).append("]: ").append(vertices[i]).append("\n");
		}
		for (int i = 0; i < gradients.length; i++) {
			s.append("  gradient[").append(i).append("]: ").append(gradients[i]).append("\n");
		}
		return s.toString();
	}
}
