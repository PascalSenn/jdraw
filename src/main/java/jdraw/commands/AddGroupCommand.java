package jdraw.commands;

import jdraw.figures.Group;
import jdraw.framework.*;

import java.util.Comparator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class AddGroupCommand implements DrawCommand {
    protected Group g;
    protected final DrawView view;
    protected final DrawModel model;

    public AddGroupCommand(Group f, DrawView view, DrawModel model) {
        this.g = f;
        this.view = view;
        this.model = model;
    }

    @Override
    public void redo() {
        g.getFigureParts().forEach(ge -> {
            model.removeFigure(ge.figure);
            view.removeFromSelection(ge.figure);
        });
        model.addFigure(g);
        view.addToSelection(g);
    }

    @Override
    public void undo() {
        model.removeFigure(g);
        view.removeFromSelection(g);
        StreamSupport.stream(g.getFigureParts().spliterator(), false)
                .sorted(Comparator.comparingInt(l -> l.index))
                .forEach(ge -> {
                    model.addFigure(ge.figure);
                    model.setFigureIndex(ge.figure, ge.index);
                    view.addToSelection(ge.figure);
                });
    }


}
