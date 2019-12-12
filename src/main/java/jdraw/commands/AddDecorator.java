package jdraw.commands;

import jdraw.figures.decorators.FigureDecoratorBase;
import jdraw.framework.DrawCommand;
import jdraw.framework.DrawContext;
import jdraw.framework.Figure;

public class AddDecorator implements DrawCommand {
    protected FigureDecoratorBase f;
    protected DrawContext context;

    public AddDecorator(FigureDecoratorBase f, DrawContext context) {
        this.f = f;
        this.context = context;

    }

    @Override
    public void redo() {
        context.getModel().addFigure(f);
        context.getModel().removeFigure(f.getFigure());
    }

    @Override
    public void undo() {
        context.getModel().removeFigure(f);
        context.getModel().addFigure(f.getFigure());
    }

    public static AddDecorator create(FigureDecoratorBase f, DrawContext context) {
        return new AddDecorator(f, context);
    }
}
