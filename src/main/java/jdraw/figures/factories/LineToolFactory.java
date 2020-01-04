package jdraw.figures.factories;

import jdraw.figures.CircleTool;
import jdraw.figures.LineTool;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class LineToolFactory extends FactoryBase {
    @Override
    public DrawTool createTool(DrawContext context) {
        return new LineTool(getName(), getIconName(), context);
    }
}
