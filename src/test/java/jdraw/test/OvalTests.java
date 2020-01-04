package jdraw.test;

import jdraw.figures.Group;
import jdraw.figures.Line;
import jdraw.figures.Oval;
import jdraw.framework.Figure;

public class OvalTests extends AbstractFigureTest {
	
    @Override
    public Figure createFigure(int x, int y, int w, int h) {

        return new Oval(x, y, w, h);
    }
}
