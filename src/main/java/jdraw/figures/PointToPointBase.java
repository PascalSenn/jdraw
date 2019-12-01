package jdraw.figures;

import java.awt.*;

/**
 * Every child of this strategy can be drawn  inside of a rectangle.
 * A line is i.E. is the diagonal axis
 * An ovals outer bounds are also a rectangle
 *
 * @param <T>
 */
public abstract class PointToPointBase<T extends Shape> extends FigureBase {
    private Point start;
    private Point end;
    private T shape;

    public PointToPointBase(int x1, int y1, int x2, int y2) {
        this.shape = this.createShape();
        start = new Point(x1, y1);
        end = new Point(x1, y1);
    }

    public PointToPointBase(PointToPointBase<T> source) {
        this.shape = this.createShape();
        start = new Point(source.start.x, source.start.y);
        end = new Point(source.end.x, source.end.y);
        this.setBoundsOnShape(this.shape, start, end);
    }

    /**
     * Draw the shape into a recatnlge to the given graphics context.
     *
     * @param g the graphics context to use for drawing.
     */
    @Override
    public final void draw(Graphics g) {
        g.setColor(Color.WHITE);
        drawFill(g, start, end);
        g.setColor(Color.BLACK);
        drawBorder(g, start, end);
    }

    protected abstract void drawFill(Graphics g, Point start, Point end);

    protected abstract void drawBorder(Graphics g, Point start, Point end);

    public abstract void setBoundsOnShape(T shape, Point origin, Point corner);

    @Override
    public final void setBounds(Point origin, Point corner) {
        start = origin;
        end = corner;
        setBoundsOnShape(shape, origin, corner);
        handleFigureChange();
    }

    @Override
    public final void move(int dx, int dy) {
        if (dx != 0 || dy != 0) {
            this.start = new Point(this.start.x + dx, this.start.y + dy);
            this.end = new Point(this.end.x + dx, this.end.y + dy);
            handleFigureChange();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return shape.contains(x, y);
    }

    @Override
    public final Rectangle getBounds() {
        return shape.getBounds();
    }

    protected abstract T createShape();


}
