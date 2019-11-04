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
        // XXX You copy the content of the listener list, but I would consider that this is not 
        //     state of the figure itself but rather state of the context, i.e. this content must
        //     not be cloned. What do you do with a cloned figure? You might add it into a new model, 
        //     but then a new listener will be registered in the figure which was added to the figure.
        //     Another scenario could be to add the figure into another model. Why do we then still 
        //     need/keep the listener of the original model in the cloned figure?
        // 	   => I would simply initialize the changeListeners list with an empty list (could be done using a field initializator).
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
