package jdraw.handles;

import jdraw.framework.Figure;

import java.awt.*;

public class LineStartHandle extends LineHandleBase {

    public LineStartHandle(Figure owner) {
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
        setBounds(endPoint, new Point(bounds.x + bounds.width, bounds.y + bounds.height));
    }
}
