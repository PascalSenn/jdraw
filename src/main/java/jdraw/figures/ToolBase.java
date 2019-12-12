/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.figures;

import jdraw.commands.AddCommand;
import jdraw.framework.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * This tool defines a mode for drawing rectangles.
 *
 * @author Christoph Denzler
 * @see jdraw.framework.Figure
 */
public abstract class ToolBase implements DrawTool {
    /**
     * the image resource path.
     */
    private static final String IMAGES = "/images/";

    /**
     * The context we use for drawing.
     */
    protected final DrawContext context;

    /**
     * The context's view. This variable can be used as a shortcut, i.e.
     * instead of calling context.getView().
     */
    protected final DrawView view;
    protected final DrawCommandHandler commandHandler;

    /**
     * Temporary variable.
     * During rectangular shape creation this variable refers to the point the
     * mouse was first pressed.
     */
    protected Point anchor = null;

    public final String name;

    /**
     * Create a new rectangular shape tool for the given context.
     *
     * @param context a context to use this tool in.
     */
    public ToolBase(String name, DrawContext context) {
        this.name = name;
        this.context = context;
        this.view = context.getView();
        this.commandHandler = context.getModel().getDrawCommandHandler();
    }

    /**
     * Deactivates the current mode by resetting the cursor
     * and clearing the status bar.
     *
     * @see DrawTool#deactivate()
     */
    @Override
    public final void deactivate() {
        this.context.showStatusText("");
    }

    /**
     * Activates the Rectangle Mode. There will be a
     * specific menu added to the menu bar that provides settings for
     * Rectangle attributes
     */
    @Override
    public final void activate() {
        this.context.showStatusText("Rectangle Mode");
    }

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
    public abstract void mouseDown(int x, int y, MouseEvent e);

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
    public abstract void mouseDrag(int x, int y, MouseEvent e);

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
    public abstract void mouseUp(int x, int y, MouseEvent e);

    @Override
    public final Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
    }

    @Override
    public final Icon getIcon() {
        return new ImageIcon(getClass().getResource(IMAGES + name.toLowerCase() + ".png"));
    }

    @Override
    public final String getName() {
        return name;
    }

    protected void addAddCommand(Figure f) {

        this.commandHandler.addCommand(AddCommand.create(f, context));
    }

}
