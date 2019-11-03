/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.handles.*;

/**
 * Represents rectangles in JDraw.
 *
 * @author Christoph Denzler
 */
public class Rect extends RectangularShapeBase<Rectangle2D> {
    private static final long serialVersionUID = 9120181044386552132L;

    /**
     * Create a new rectangle of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public Rect(int x, int y, int w, int h) {
        super(x, y, w, h);
    }


    public Rect(Rect rect) {
        super(rect);
    }

    @Override
    protected Rectangle2D createShape() {
        return new Rectangle2D.Double();
    }

    @Override
    protected void drawFill(Graphics g, int x, int y, int width, int height) {
        g.fillRect(x, y, width, height);
    }

    @Override
    protected void drawBorder(Graphics g, int x, int y, int width, int height) {
        g.drawRect(x, y, width, height);
    }

    @Override
    public void setBoundsOnShape(Rectangle2D shape, Point origin, Point corner) {
        shape.setFrameFromDiagonal(origin, corner);
    }

    @Override
    public void move(Rectangle2D shape, int dx, int dy) {
        var bounds = shape.getBounds();
        shape.setFrame(bounds.x + dx, bounds.y + dy, bounds.width, bounds.height);
    }

    /**
     * Returns a list of 8 handles for this Rectangle.
     *
     * @return all handles that are attached to the targeted figure.
     * @see jdraw.framework.Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        var list = new ArrayList<FigureHandle>();
        list.add(new NorthHandle(this));
        list.add(new NorthEastHandle(this));
        list.add(new EastHandle(this));
        list.add(new NorthWestHandle(this));
        list.add(new SouthEastHandle(this));
        list.add(new SouthWestHandle(this));
        list.add(new WestHandle(this));
        list.add(new SouthHandle(this));
        return list;
    }

    @Override
    public Figure clone() {
        return new Rect(this);
    }


}
