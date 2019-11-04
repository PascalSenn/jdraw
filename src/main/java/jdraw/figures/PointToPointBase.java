package jdraw.figures;

import java.awt.*;
import java.awt.geom.Line2D;

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
        // XXX I missed an invocation to setBoundsOnShape (see commennts below). At least, in class
        //     RectangularShapeBase you invoke this.setBounds(start, end); from the regular constructor.
    }

    public PointToPointBase(PointToPointBase<T> source) {
    	// XXX here the invocation of the copy constructor of the base class is missing (well, by chance it is the
    	//     correct behavior as the conten of the listener list is not copied.
        this.shape = this.createShape(); // XXX this is a little strange for me, but I understand it.
        								 //     Why not invoking createShape once the two points have been created,
        								 //     then the subclass could create the correct instance base on the complete information.
        start = new Point(source.start.x, source.start.y); // XXX you could also invoke clone on source.start and source.end
        end = new Point(source.end.x, source.end.y);
        this.setBoundsOnShape(this.shape, start, end);
        // XXX why is setBoundsOnShape not invoked from the above constructor? Probably you assume that setBounds will be
        //     called anyways, but this is only the case if you look at the current usage by the JDraw UI, but such a
        //     PointToPoint figure could also simply be created using the above constructor.
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
        handleFigureChange(); // XXX actually a notification should only be raised if the figure changed. This could be tested in this method
        					  //     by comparing the original and the new origin/corner points.
    }

    @Override
    public final void move(int dx, int dy) {
        if (dx != 0 && dy != 0) { // XXX already mentioned the problem of this test, i.e. && should be replaced by ||
            this.start = new Point(this.start.x + dx, this.start.y + dy);
            this.end = new Point(this.end.x + dx, this.end.y + dy);
            handleFigureChange();
        }
    }

    @Override
    public boolean contains(int x, int y) {
        return shape.contains(x, y); // XXX well, look into the implementation of Line2D.Double.contains, and you understand why your lines are not selectable.
    }

    @Override
    public final Rectangle getBounds() {
        return shape.getBounds();
    }

    protected abstract T createShape();
}
