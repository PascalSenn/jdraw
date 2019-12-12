package jdraw.figures.decorators;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.awt.*;
import java.util.List;

public abstract class FigureDecoratorBase implements Figure {
    protected final Figure figure;


    public FigureDecoratorBase(Figure figure) {
        this.figure = figure;
    }

    @Override
    public void draw(Graphics g) {
        figure.draw(g);
    }

    @Override
    public void move(int dx, int dy) {
        figure.move(dx, dy);
    }

    @Override
    public boolean contains(int x, int y) {
        return figure.contains(x, y);
    }

    @Override
    public void setBounds(Point origin, Point corner) {
        figure.setBounds(origin, corner);
    }

    @Override
    public Rectangle getBounds() {
        return figure.getBounds();
    }

    @Override
    public List<FigureHandle> getHandles() {
        return figure.getHandles();
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        figure.addFigureListener(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        figure.removeFigureListener(listener);
    }

    @Override
    public abstract Figure clone();


    public Figure getFigure() {
        return figure;
    }
}
