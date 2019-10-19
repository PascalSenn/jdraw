package jdraw.handles;

import jdraw.framework.Figure;

import java.awt.*;

public class SouthWestHandle extends FigureHandleBase {

    public SouthWestHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        var bounds = this.getOwner().getBounds();
        var x = bounds.x ;
        var y = bounds.y+ bounds.height;
        return new Point(x, y);
    }


    @Override
    protected void interactionChanged(Point startPoint, Point endPoint) {
        var bounds = this.getOwner().getBounds();
        getOwner().setBounds(new Point(endPoint.x, bounds.y), new Point(bounds.x + bounds.width, endPoint.y));
    }
}
