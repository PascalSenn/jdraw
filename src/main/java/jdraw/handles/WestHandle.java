package jdraw.handles;

import jdraw.framework.Figure;

import java.awt.*;

public class WestHandle extends FigureHandleBase {

    public WestHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        var bounds = this.getOwner().getBounds();
        var x = bounds.x ;
        var y = bounds.y  + bounds.height / 2;
        return new Point(x, y);
    }


    @Override
    protected void interactionChanged(Point startPoint, Point endPoint) {
        var bounds = this.getOwner().getBounds();
        var delta = endPoint.x - startPoint.x;
        getOwner().setBounds(new Point(bounds.x + delta, bounds.y), new Point(bounds.x + bounds.width, bounds.y + bounds.height));
    }
}
