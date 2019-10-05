/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.framework.DrawContext;

import java.awt.*;

/**
 * This tool defines a mode for drawing lines.
 *
 * @author Christoph Denzler
 * @see jdraw.framework.Figure
 */
public class LineTool extends RectangularShapeToolBase {

	/**
	 * Create a new line tool for the given context.
	 *
	 * @param context a context to use this tool in.
	 */
	public LineTool(DrawContext context) {
		super("Line", context);
	}


	@Override
	public RectangularShapeStrategy createFigure(int x, int y) {
		return new Line(x, y, 0, 0);
	}

	@Override
	public void updateFigure(int x, int y) {
		newShape.setBounds(anchor, new Point(x,y));
	}
}
