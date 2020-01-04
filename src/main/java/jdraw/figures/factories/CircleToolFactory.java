package jdraw.figures.factories;

import jdraw.figures.CircleTool;
import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawToolFactory;

public class CircleToolFactory extends FactoryBase {
    @Override
    public DrawTool createTool(DrawContext context) {
        return new CircleTool(getName(), getIconName(), context);
    }
}
