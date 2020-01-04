package jdraw.figures.factories;

import jdraw.framework.DrawContext;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawToolFactory;

public abstract class FactoryBase implements DrawToolFactory {
    private String name;
    private String iconName;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getIconName() {
        return iconName;
    }

    @Override
    public void setIconName(String name) {
        this.iconName = name;
    }

    @Override
    public abstract DrawTool createTool(DrawContext context);
}
