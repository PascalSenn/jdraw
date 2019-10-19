package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.RectangularShape;

/**
 * Every child of this base can be drawn  inside of a rectangle.
 * A line is i.E. is the diagonal axis
 * An ovals outer bounds are also a rectangle
 *
 * @param <T>
 */
public abstract class RectangularShapeBase<T extends Shape> extends FigureBase {

    private T shape;


    public RectangularShapeBase(T shape, int x, int y, int w, int h) {
        this.shape = shape;
        this.setBounds(new Point(x, y), new Point(x + w, y + h));
    }

    /**
     * Draw the shape into a recatnlge to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    @Override
    public final void draw(Graphics g) {
        g.setColor(Color.WHITE);
        drawFill(g, getBounds().x, getBounds().y, getBounds().width, getBounds().height);
        g.setColor(Color.BLACK);
        drawBorder(g, getBounds().x, getBounds().y, getBounds().width, getBounds().height);
    }

    protected abstract void drawFill(Graphics g, int x, int y, int width, int height);

    protected abstract void drawBorder(Graphics g, int x, int y, int width, int height);

    public abstract void setBoundsOnShape(T shape, Point origin, Point corner);

    @Override
    public final void setBounds(Point origin, Point corner) {
        setBoundsOnShape(shape, origin, corner);
        handleFigureChange();
    }
    public abstract void move(T shape, int dx, int dy) ;
    @Override
    public final void move(int dx, int dy) {
        if (dx != 0 && dy != 0) {
            move(shape, dx, dy);
            handleFigureChange();
        }
    }

    @Override
    public final boolean contains(int x, int y) {
        return shape.contains(x, y);
    }

    @Override
    public final Rectangle getBounds() {
        return shape.getBounds();
    }


}
