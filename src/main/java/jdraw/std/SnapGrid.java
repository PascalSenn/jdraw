package jdraw.std;

import jdraw.framework.DrawGrid;
import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SnapGrid implements DrawGrid {
    public static final int MIN_DISTANCE_TO_SNAP = 20;
    private boolean activated = false;
    private List<Figure> figures;
    private Set<Figure> selection;

    @Override
    public Point constrainPoint(Point p) {
        if (figures == null) {
            throw new UnsupportedOperationException("No figures found for snap grid");
        }
        if (selection == null) {
            throw new UnsupportedOperationException("No handles found for snap grid");
        }
        if (p == null) {
            throw new IllegalArgumentException("p");
        }

        double lastDistance = Double.MAX_VALUE;
        FigureHandle lastHandle = null;
        // this is to check if the bounds of a figure is intersected
        // to later check the handles. This is just because of performance
        // reasons. We don't want to check all of the handles
        var intersectionRectangle = new Rectangle();
        intersectionRectangle.setFrameFromCenter(p.x, p.y, p.x + MIN_DISTANCE_TO_SNAP, p.y + MIN_DISTANCE_TO_SNAP);
        for (var figure : figures) {
            if (!selection.contains(figure) &&
                    figure.getBounds().intersects(intersectionRectangle)) {
            		// XXX good optimization!
                for (var handle : figure.getHandles()) {
                    var distance = Point2D.distance(p.x, p.y, handle.getLocation().x, handle.getLocation().y);
                    if (distance < lastDistance) {
                        lastDistance = distance;
                        lastHandle = handle;
                    }
                }
            }

        }
        if (lastDistance < MIN_DISTANCE_TO_SNAP) {
            return lastHandle.getLocation();
        }
        return p;
    }

    @Override
    public int getStepX(boolean right) {
        return MIN_DISTANCE_TO_SNAP;
    }

    @Override
    public int getStepY(boolean down) {
        return MIN_DISTANCE_TO_SNAP;
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

        this.figures = figures.collect(Collectors.toList());
        this.selection = Set.copyOf(selection);
    }


    @Override
    public void mouseUp() {
    	// XXX mouseUp should probably clear the figures and selection fields, otherwise they are alwyas set after the first invocation.
    }
}
