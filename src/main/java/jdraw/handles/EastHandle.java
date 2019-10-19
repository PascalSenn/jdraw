package jdraw.handles;

import jdraw.framework.Figure;

import java.awt.*;

public class EastHandle extends FigureHandleBase {

    public EastHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        var bounds = this.getOwner().getBounds();
        var x = bounds.x +  bounds.width;
        var y = bounds.y  + bounds.height / 2;
        return new Point(x, y);
    }


    @Override
    protected void interactionChanged(Point startPoint, Point endPoint) {
        var bounds = this.getOwner().getBounds();
        var delta = endPoint.x - startPoint.x;
        getOwner().setBounds(new Point(bounds.x, bounds.y), new Point(bounds.x + bounds.width + delta, bounds.y + bounds.height));
    }
}
