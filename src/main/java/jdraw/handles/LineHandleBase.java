package jdraw.handles;

import jdraw.framework.Figure;

import java.awt.*;

public abstract class LineHandleBase extends FigureHandleBase {


    public LineHandleBase(Figure owner) {
        super(owner);
    }

    protected void setBounds(Point startPoint, Point endPoint) {
        var bounds = this.getOwner().getBounds();
     //   if (bounds.x < this.endPoint.x && bounds.y < this.endPoint.y) {//default
            getOwner().setBounds(startPoint, endPoint);
    //  } else if (bounds.x > this.endPoint.x && bounds.y < this.endPoint.y) {             //left
    //
    //  } else if (bounds.x < this.endPoint.x && bounds.y > this.endPoint.y) {                  //bottom
    //
    //  } else {         // inverted
    //      getOwner().setBounds(endPoint, startPoint);
    //  }
    }


}