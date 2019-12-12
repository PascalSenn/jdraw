package jdraw.figures;

import jdraw.framework.*;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class Group implements Figure, FigureGroup {
    private final List<GroupElement> figures;

    public Group(List<Figure> figures, DrawModel model) {
        this.figures = figures.stream().map(f -> GroupElement.create(f, model)).collect(Collectors.toList());
    }

    public Group(Group group) {
        this.figures = group.figures
                .stream()
                .map(x -> x.clone())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public void draw(Graphics g) {
        figures.stream().map(group -> group.figure).forEach(f -> f.draw(g));
    }

    @Override
    public void move(int dx, int dy) {
        figures.stream().map(g -> g.figure).forEach(f -> f.move(dx, dy));
    }

    @Override
    public boolean contains(int x, int y) {
        return figures.stream().map(g -> g.figure).anyMatch(f -> f.contains(x, y));
    }

    @Override
    public void setBounds(Point origin, Point corner) {

    }

    @Override
    public Rectangle getBounds() {
        return figures.stream().map(g -> g.figure).map(x -> x.getBounds()).reduce((l, r) -> l.union(r)).orElse(new Rectangle());
    }

    @Override
    public List<FigureHandle> getHandles() {
        return figures.stream().map(g -> g.figure)
                .flatMap(f -> f.getHandles().stream())
                .collect(toList());
    }

    @Override
    public void addFigureListener(FigureListener listener) {
        figures.stream().map(g -> g.figure).forEach(f -> f.addFigureListener(listener));
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        figures.stream().map(g -> g.figure).forEach(f -> f.removeFigureListener(listener));
    }

    @Override
    public Figure clone() {
        return new Group(this);
    }

    @Override
    public Iterable<GroupElement> getFigureParts() {
        return figures;
    }
}
