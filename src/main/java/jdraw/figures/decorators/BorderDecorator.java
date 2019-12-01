package jdraw.figures.decorators;

import jdraw.framework.Figure;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BorderDecorator extends FigureDecoratorBase {
    private static final int SPACING = 3;

    public BorderDecorator(Figure figure) {
        super(figure);
    }

    @Override
    public void draw(Graphics g) {
        var bounds = figure.getBounds();
        g.setColor(Color.WHITE);
        g.drawLine(bounds.x - SPACING, bounds.y - SPACING, bounds.x + bounds.width + SPACING, bounds.y - SPACING);
        g.drawLine(bounds.x - SPACING, bounds.y - SPACING, bounds.x - SPACING, bounds.y + bounds.height + SPACING);
        g.setColor(Color.DARK_GRAY);
        g.drawLine(bounds.x - SPACING,
                bounds.y + bounds.height + SPACING,
                bounds.x + bounds.width + SPACING,
                bounds.y + bounds.height + SPACING);

        g.drawLine(bounds.x + bounds.width + SPACING,
                bounds.y - SPACING,
                bounds.x + bounds.width + SPACING,
                bounds.y + bounds.height + SPACING);
        super.draw(g);

    }


    @Override
    public Figure clone() {
        return new BorderDecorator(figure);
    }
}
