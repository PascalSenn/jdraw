package jdraw.figures;

import jdraw.framework.DrawModel;
import jdraw.framework.Figure;

import java.util.List;
import java.util.stream.Collectors;

public class GroupElement {
    public final Figure figure;
    public final int index;

    private GroupElement(Figure f, int index) {
        this.figure = f;
        this.index = index;
    }

    public static GroupElement create(Figure figure, DrawModel model) {
        List<Figure> figures = model.getFigures().collect(Collectors.toList());
        var index = figures.indexOf(figure);
        if (index == -1) {
            throw new IllegalArgumentException();
        }
        return new GroupElement(figure, index);
    }

    public GroupElement clone() {
        return new GroupElement(figure.clone(), index);
    }
}
