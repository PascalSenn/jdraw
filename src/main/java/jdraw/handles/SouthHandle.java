package jdraw.handles;

import jdraw.framework.Figure;

import java.awt.*;

public class SouthHandle extends FigureHandleBase {

    public SouthHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        var bounds = this.getOwner().getBounds();
        var x = bounds.x + bounds.width / 2;
        var y = bounds.y + bounds.height;
        return new Point(x, y);
    }


    @Override
    protected void interactionChanged(Point startPoint, Point endPoint) {
        var bounds = this.getOwner().getBounds();
        var delta = endPoint.y - startPoint.y;
        getOwner().setBounds(new Point(bounds.x, bounds.y ), new Point(bounds.x + bounds.width, bounds.y + bounds.height+ delta));
    }
}
