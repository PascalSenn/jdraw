package jdraw.handles;

import jdraw.framework.Figure;

import java.awt.*;

public class NorthEastHandle extends FigureHandleBase {

    public NorthEastHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        var bounds = this.getOwner().getBounds();
        var x = bounds.x +  bounds.width;
        var y = bounds.y;
        return new Point(x, y);
    }


    @Override
    protected void interactionChanged(Point startPoint, Point endPoint) {
        var bounds = this.getOwner().getBounds();
        getOwner().setBounds(new Point(bounds.x, endPoint.y), new Point(endPoint.x  , bounds.y + bounds.height));
    }
}
