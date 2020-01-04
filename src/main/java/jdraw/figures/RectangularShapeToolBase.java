/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @author Christoph Denzler
 * @see jdraw.framework.Figure
 */
public abstract class RectangularShapeToolBase extends ToolBase {
    /**
     * Temporary variable. During  shape creation (during a
     * mouse down - mouse drag - mouse up cycle) this variable refers
     * to the new rectangle that is inserted.
     */
    protected RectangularShapeBase newShape = null;

    /**
     * Temporary variable.
     * During rectangular shape creation this variable refers to the point the
     * mouse was first pressed.
     */
    protected Point anchor = null;

    /**
     * Create a new rectangular shape tool for the given context.
     *
     * @param context a context to use this tool in.
     */
    public RectangularShapeToolBase(String name, String iconName, DrawContext context) {
        super(name, iconName, context);
    }


    /**
     * Initializes a new Rectangle object
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @see DrawTool#mouseDown(int, int, MouseEvent)
     */
    public abstract RectangularShapeBase createFigure(int x, int y);

    public abstract void updateFigure(int x, int y);

    /**
     * Initializes a new Figure Recangular Shape object by setting an anchor
     * point where the mouse was pressed. A new Rectangle Shape is create via `createFigure` and then
     * added to the model.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were pressed.
     * @see DrawTool#mouseDown(int, int, MouseEvent)
     */
    @Override
    public final void mouseDown(int x, int y, MouseEvent e) {
        if (newShape != null) {
            throw new IllegalStateException();
        }
        anchor = new Point(x, y);
        newShape = createFigure(x, y);
        this.addAddCommand(newShape);
        view.getModel().addFigure(newShape);
    }

    /**
     * During a mouse drag, the Rectangle will be resized according to the mouse
     * position. The status bar shows the current size.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were
     *          pressed.
     * @see DrawTool#mouseDrag(int, int, MouseEvent)
     */
    @Override
    public final void mouseDrag(int x, int y, MouseEvent e) {
        updateFigure(x, y);
        java.awt.Rectangle r = newShape.getBounds();
        this.context.showStatusText("w: " + r.width + ", h: " + r.height);
    }

    /**
     * When the user releases the mouse, the Rectangle object is updated
     * according to the color and fill status settings.
     *
     * @param x x-coordinate of mouse
     * @param y y-coordinate of mouse
     * @param e event containing additional information about which keys were
     *          pressed.
     * @see DrawTool#mouseUp(int, int, MouseEvent)
     */
    @Override
    public final void mouseUp(int x, int y, MouseEvent e) {
        newShape = null;
        anchor = null;
        this.context.showStatusText("Rectangle Mode");
    }
}
