package jdraw.figures.decorators;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BundleDecorator extends FigureDecoratorBase {
    private static final int SPACING = 3;

    public BundleDecorator(Figure figure) {
        super(figure);
    }


    @Override
    public List<FigureHandle> getHandles() {
        return new ArrayList<>();
    }

    @Override
    public void setBounds(Point origin, Point corner) {

    }

    @Override
    public Figure clone() {
        return new BundleDecorator(figure);
    }
}
