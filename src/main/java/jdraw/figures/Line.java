/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.List;

/**
 * Represents line in JDraw.
 *
 * @author Christoph Denzler
 */
public class Line extends RectangularShapeStrategy<Line2D> {
    private static final long serialVersionUID = 9120181044386552132L;

    /**
     * Create a new Line of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public Line(int x, int y, int w, int h) {
        super(new Line2D.Double(), x,y,w,h);
    }


    @Override
    protected void drawFill(Graphics g, int x, int y, int width, int height) {
    }

    @Override
    protected void drawBorder(Graphics g, int x, int y, int width, int height) {
        g.drawLine(x,y,x+ width,y+height);
    }

    @Override
    public void setBoundsOnShape(Line2D shape, Point origin, Point corner) {
        shape.setLine(origin, corner);
    }

    /**
     * Returns a list of 8 handles for this Shape.
     *
     * @return all handles that are attached to the targeted figure.
     * @see Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        return null;
    }

    @Override
    public Figure clone() {
        return null;
    }


}
