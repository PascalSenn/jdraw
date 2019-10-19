package jdraw.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public class NorthHandle extends FigureHandleBase {

    public NorthHandle(Figure owner) {
        super(owner);
    }

    @Override
    public Point getLocation() {
        var bounds = this.getOwner().getBounds();
        var x = bounds.x + bounds.width / 2;
        var y = bounds.y;
        return new Point(x, y);
    }


    @Override
    protected void interactionChanged(Point startPoint, Point endPoint) {
        var bounds = this.getOwner().getBounds();
        var delta = endPoint.y - startPoint.y;
        getOwner().setBounds(new Point(bounds.x, bounds.y + delta), new Point(bounds.x + bounds.width, bounds.y + bounds.height));
    }
}
