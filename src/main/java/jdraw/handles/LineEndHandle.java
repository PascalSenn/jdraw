package jdraw.handles;

import jdraw.framework.Figure;

import java.awt.*;

public class LineEndHandle extends LineHandleBase {

    public LineEndHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        var bounds = this.getOwner().getBounds();
        var x = bounds.x + bounds.width;
        var y = bounds.y + bounds.height;
        return new Point(x, y);
    }


    @Override
    protected void interactionChanged(Point startPoint, Point endPoint) {
        var bounds = this.getOwner().getBounds();
        setBounds(new Point(bounds.x, bounds.y), endPoint);
    }
}
