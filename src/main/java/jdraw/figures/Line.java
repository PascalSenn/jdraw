/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.handles.LineEndHandle;
import jdraw.handles.LineStartHandle;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents line in JDraw.
 *
 * @author Christoph Denzler
 */
public class Line extends PointToPointBase<Line2D> {
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
        super(x, y, w, h);
    }

    public Line(Line line) {
        super(line);
    }

    @Override
    protected void drawFill(Graphics g, Point start, Point end) {

    }

    @Override
    protected void drawBorder(Graphics g, Point start, Point end) {
        g.drawLine(start.x, start.y, end.x, end.y);
    }

    @Override
    public void setBoundsOnShape(Line2D shape, Point origin, Point corner) {
        shape.setLine(origin, corner);
    }

    @Override
    protected Line2D createShape() {
        return new Line2D.Double();
    }

    /**
     * Returns a list of 8 handles for this Shape.
     *
     * @return all handles that are attached to the targeted figure.
     * @see Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        var list = new ArrayList<FigureHandle>();
        list.add(new LineEndHandle(this));
        list.add(new LineStartHandle(this));
        return list;
    }

    @Override
    public Figure clone() {
        return new Line(this);
    }


}
