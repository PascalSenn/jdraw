/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import java.awt.Point;

import jdraw.framework.DrawContext;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @author Christoph Denzler
 * @see jdraw.framework.Figure
 */
public class RectTool extends RectangularShapeToolBase {


    /**
     * Create a new rectangle tool for the given context.
     *
     * @param context a context to use this tool in.
     */
    public RectTool(DrawContext context) {
        this("Rectangle", "rectangle.png", context);
    }

    public RectTool(String name, String iconName, DrawContext context) {
        super(name, iconName, context);
    }


    @Override
    public RectangularShapeBase createFigure(int x, int y) {
        return new Rect(x, y, 0, 0);
    }

    @Override
    public void updateFigure(int x, int y) {
        newShape.setBounds(anchor, new Point(x, y));
    }
}
