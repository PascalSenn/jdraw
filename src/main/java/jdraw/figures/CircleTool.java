/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.framework.DrawContext;

import java.awt.*;

/**
 * This tool defines a mode for drawing circles.
 *
 * @author Christoph Denzler
 * @see jdraw.framework.Figure
 */
public class CircleTool extends RectangularShapeToolBase {

	/**
	 * Create a new rectangle tool for the given context.
	 *
	 * @param context a context to use this tool in.
	 */
	public CircleTool(DrawContext context) {
		super("Circle", context);
	}


	@Override
	public RectangularShapeBase createFigure(int x, int y) {
		return new Oval(x, y, 0, 0);
	}

	@Override
	public void updateFigure(int x, int y) {
		var delta = new Point(x- anchor.x, y- anchor.y);
		if(Math.abs(delta.x) < Math.abs(delta.y)) {
			delta.x = delta.y;
		} else {
			delta.y = delta.x;
		}
		delta.x += anchor.x;
		delta.y += anchor.y;
		newShape.setBounds(anchor, delta);
	}
}
