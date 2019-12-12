/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.std;

import jdraw.commands.CompositeCommand;
import jdraw.framework.DrawCommand;
import jdraw.framework.DrawCommandHandler;

import java.util.Stack;

/**
 * Provides an empty command handler. This class provides an empty dummy implementation of the draw command
 * handler. It enables the application to start up correctly and to behave meaningful, but with the limitation
 * that it does not provide any undo/redo behavior.
 *
 * @author Christoph. Denzler
 */
public class EmptyDrawCommandHandler implements DrawCommandHandler {
    private Stack<DrawCommand> done = new Stack<>();
    private Stack<DrawCommand> toDo = new Stack<>();
    private CompositeCommand macro = null;

    @Override
    public void addCommand(DrawCommand cmd) {
        if (isScriptRunning()) {
            macro.addCommand(cmd);
        } else {
            done.push(cmd);
        }
    }

    @Override
    public void undo() {
        if (undoPossible()) {
            var cmd = done.pop();
            cmd.undo();
            toDo.push(cmd);
        }
    }

    @Override
    public void redo() {
        if (redoPossible()) {
            var cmd = toDo.pop();
            cmd.redo();
            done.push(cmd);
        }
    }

    @Override
    public boolean undoPossible() {
        return done.size() > 0;
    }

    @Override
    public boolean redoPossible() {
        return toDo.size() > 0;
    }

    @Override
    public void beginScript() {
        macro = new CompositeCommand();
    }

    @Override
    public void endScript() {
        if (!isScriptRunning()) throw new IllegalStateException("No script is running");
        if (macro.size() == 1) {
            done.push(macro.getCommands().iterator().next());
        }
        if (macro.size() > 0) {
            done.push(macro);
        }
        macro = null;
    }

    @Override
    public void clearHistory() {
        toDo.clear();
        done.clear();
        macro = null;
    }

    private boolean isScriptRunning() {
        return macro != null;
    }
}
