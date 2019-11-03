package jdraw.figures;

import jdraw.framework.Figure;
import jdraw.framework.FigureEvent;
import jdraw.framework.FigureHandle;
import jdraw.framework.FigureListener;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public abstract class FigureBase implements Figure {
    private final List<FigureListener> changeListeners;

    public FigureBase(FigureBase figureBase) {
        this.changeListeners = new CopyOnWriteArrayList<>(figureBase.changeListeners);
    }

    public FigureBase() {
        this.changeListeners = new CopyOnWriteArrayList<>();
    }


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
