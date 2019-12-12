package jdraw.commands;

import jdraw.framework.DrawCommand;
import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

public class AddCommand implements DrawCommand {
    private Figure f;
    private DrawContext context;

    public AddCommand(Figure f, DrawContext context) {
        this.f = f;
        this.context = context;

    }

    @Override
    public void redo() {
        context.getModel().addFigure(f);
    }

    @Override
    public void undo() {
        context.getModel().removeFigure(f);
    }

    public static AddCommand create(Figure f, DrawContext context) {
        return new AddCommand(f, context);
    }
}
