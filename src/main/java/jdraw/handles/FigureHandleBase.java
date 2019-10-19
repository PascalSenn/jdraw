package jdraw.handles;

import jdraw.framework.DrawView;
import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class FigureHandleBase implements FigureHandle {

    private static final int HANDLE_SIZE = 5;

    private final Figure owner;
    private Rectangle rectangle;

    public FigureHandleBase(Figure owner) {
        this.owner = owner;
        this.rectangle = new Rectangle();
        // I could also use the draw method to update the state. I prefere an action listener
        owner.addFigureListener(this::onChangeListener);
        update();
    }

    private void onChangeListener(FigureEvent e) {
        update();
    }

    public void update() {
        var location = this.getLocation();
        this.rectangle.setFrame(location.x - HANDLE_SIZE / 2, location.y - HANDLE_SIZE / 2, HANDLE_SIZE, HANDLE_SIZE);
    }

    @Override
    public Figure getOwner() {
        return this.owner;
    }

    @Override
    public boolean contains(int x, int y) {
        return this.rectangle.contains(x, y);
    }

    @Override
    public void draw(Graphics g) {
        var graphics = ((Graphics2D) g);
        graphics.setColor(Color.WHITE);
        graphics.fill(this.rectangle);
        graphics.setColor(Color.BLACK);
        graphics.draw(this.rectangle);
    }


    @Override
    public Cursor getCursor() {
        return Cursor.getPredefinedCursor(Cursor.HAND_CURSOR);
    }

    protected Point startPoint;
    protected Point intermediateStart;
    protected Point intermediateEnd;
    protected Point endPoint;

    @Override
    public void startInteraction(int x, int y, MouseEvent e, DrawView v) {
        startPoint = new Point(x, y);
        intermediateStart= startPoint;
    }

    @Override
    public void dragInteraction(int x, int y, MouseEvent e, DrawView v) {
        endPoint = new Point(x, y);
        intermediateEnd = endPoint;
        interactionChanged(intermediateStart, intermediateEnd);
        intermediateStart = endPoint;
    }

    @Override
    public void stopInteraction(int x, int y, MouseEvent e, DrawView v) {
        endPoint = new Point(x, y);
        intermediateEnd = endPoint;
        interactionFinished(startPoint, endPoint);

    }

    protected void interactionFinished(Point startPoint, Point endPoint) {

    }

    protected void interactionChanged(Point startPoint, Point endPoint) {

    }

}
