package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class FigureBase implements Figure {

    private final List<FigureListener> changeListeners = new CopyOnWriteArrayList<>();

    @Override
    public void addFigureListener(FigureListener listener) {
        changeListeners.add(listener);
    }

    @Override
    public void removeFigureListener(FigureListener listener) {
        changeListeners.remove(listener);
    }

    protected void handleFigureChange() {
        notifyChangeListener(new FigureEvent(this));
    }

    protected void notifyChangeListener(FigureEvent e) {
        for (var listener : changeListeners) {
            listener.figureChanged(e);
        }
    }

    @Override
    public abstract Figure clone();
}
