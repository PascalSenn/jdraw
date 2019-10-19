/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.handles.EastHandle;
import jdraw.handles.NorthHandle;
import jdraw.handles.SouthHandle;
import jdraw.handles.WestHandle;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents ovals in JDraw.
 *
 * @author Christoph Denzler
 */
public class Oval extends RectangularShapeBase<Ellipse2D> {
    private static final long serialVersionUID = 9120181044386552132L;

    /**
     * Create a new rectangle of the given dimension.
     *
     * @param x the x-coordinate of the upper left corner of the rectangle
     * @param y the y-coordinate of the upper left corner of the rectangle
     * @param w the rectangle's width
     * @param h the rectangle's height
     */
    public Oval(int x, int y, int w, int h) {
        super(new Ellipse2D.Double(), x,y,w,h);
    }


    @Override
    protected void drawFill(Graphics g, int x, int y, int width, int height) {
        g.fillOval(x,y,width,height);
    }

    @Override
    protected void drawBorder(Graphics g, int x, int y, int width, int height) {
        g.drawOval(x,y,width,height);

    }

    @Override
    public void move(Ellipse2D shape, int dx, int dy) {
        var bounds = shape.getBounds();
        shape.setFrame(bounds.x+dx,bounds.y+dy, bounds.width, bounds.height);
    }


    @Override
    public void setBoundsOnShape(Ellipse2D shape, Point origin, Point corner) {
        shape.setFrameFromDiagonal(origin, corner);

    }

    /**
     * Returns a list of 8 handles for this Rectangle.
     *
     * @return all handles that are attached to the targeted figure.
     * @see Figure#getHandles()
     */
    @Override
    public List<FigureHandle> getHandles() {
        var list = new ArrayList<FigureHandle>();
        list.add(new NorthHandle(this));
        list.add(new EastHandle(this));
        list.add(new WestHandle(this));
        list.add(new SouthHandle(this));
        return list;
    }

    @Override
    public Figure clone() {
        return null;
    }


}
