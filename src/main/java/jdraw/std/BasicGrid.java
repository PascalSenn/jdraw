package jdraw.std;

import jdraw.framework.DrawGrid;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.util.List;
import java.util.stream.Stream;

public class BasicGrid implements DrawGrid {
    public final int step = 50;
    private boolean activated = false;

    @Override
    public Point constrainPoint(Point p) {
        int yDelta = p.y % step;
        int xDelta = p.x % step;
        yDelta = yDelta > step / 2 ? step - yDelta : -yDelta;
        xDelta = xDelta > step / 2 ? step - xDelta : -xDelta;

        return new Point(p.x + xDelta, p.y + yDelta);
    }

    @Override
    public int getStepX(boolean right) {
        return step;
    }

    @Override
    public int getStepY(boolean down) {
        return step;
    }

    @Override
    public void activate() {
        activated = true;
    }

    @Override
    public void deactivate() {
        activated = false;

    }

    @Override
    public void mouseDown(Stream<Figure> figures, List<Figure> selection) {

    }


    @Override
    public void mouseUp() {

    }
}
