package jdraw.figures.factories;

import jdraw.figures.LineTool;
import jdraw.figures.OvalTool;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class OvalToolFactory extends FactoryBase {
    @Override
    public DrawTool createTool(DrawContext context) {
        return new OvalTool(getName(), getIconName(), context);
    }
}
