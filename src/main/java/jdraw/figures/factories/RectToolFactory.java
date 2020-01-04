package jdraw.figures.factories;

import jdraw.figures.OvalTool;
import jdraw.figures.RectTool;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;

public class RectToolFactory extends FactoryBase {
    @Override
    public DrawTool createTool(DrawContext context) {
        return new RectTool(getName(), getIconName(), context);
    }
}
