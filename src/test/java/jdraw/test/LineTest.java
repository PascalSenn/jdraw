package jdraw.test;

import jdraw.figures.Line;
import jdraw.figures.Rect;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LineTest extends AbstractFigureTest {

    @Override
    public Figure createFigure(int x, int y, int w, int h) {
        return new Line(x, y, w, h);
    }
}
