package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.RectangularShape;

/**
 * Every child of this strategy can be drawn  inside of a rectangle.
 * A line is i.E. is the diagonal axis
 * An ovals outer bounds are also a rectangle
 * @param <T>
 */
public abstract class RectangularShapeStrategy<T extends Shape> extends FigureBase {
    private final Rectangle rectangle;

    private final T shape;


    public RectangularShapeStrategy(T shape, int x, int y, int w, int h) {
        this.shape = shape;
        rectangle = new Rectangle(x, y, w, h);
    }

    /**
     * Draw the shape into a recatnlge to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    @Override
    public final void draw(Graphics g) {
        g.setColor(Color.WHITE);
        drawFill(g, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        g.setColor(Color.BLACK);
        drawBorder(g, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    protected abstract void drawFill(Graphics g, int x, int y, int width, int height);

    protected abstract void drawBorder(Graphics g, int x, int y, int width, int height);

    public abstract void setBoundsOnShape(T shape, Point origin, Point corner);

    @Override
    public final void setBounds(Point origin, Point corner) {
        rectangle.setFrameFromDiagonal(origin, corner);
        handleFigureChange();
    }

    @Override
    public final void move(int dx, int dy) {
        if (dx != 0 && dy != 0) {
            rectangle.setLocation(rectangle.x + dx, rectangle.y + dy);
            handleFigureChange();
        }
    }

    @Override
    public final boolean contains(int x, int y) {
        return shape.contains(x, y);
    }

    @Override
    public final Rectangle getBounds() {
        return rectangle.getBounds();
    }


}
