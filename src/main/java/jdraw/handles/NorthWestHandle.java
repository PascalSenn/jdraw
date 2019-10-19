package jdraw.handles;

import jdraw.framework.Figure;

import java.awt.*;

public class NorthWestHandle extends FigureHandleBase {

    public NorthWestHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        var bounds = this.getOwner().getBounds();
        var x = bounds.x;
        var y = bounds.y;
        return new Point(x, y);
    }


    @Override
    protected void interactionChanged(Point startPoint, Point endPoint) {
        var bounds = this.getOwner().getBounds();
        getOwner().setBounds(new Point(endPoint.x, endPoint.y ), new Point(bounds.x + bounds.width, bounds.y + bounds.height));
    }
}
