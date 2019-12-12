package jdraw.commands;

import jdraw.framework.DrawCommand;

import java.util.ArrayList;
import java.util.List;

public class CompositeCommand implements DrawCommand {
    private List<DrawCommand> commands = new ArrayList<>();

    public void addCommand(DrawCommand command) {
        commands.add(command);
    }

    @Override
    public void redo() {
        for (var command : commands) {
            command.redo();
        }
    }

    @Override
    public void undo() {
        for (var command : commands) {
            command.undo();
        }
    }

    public int size() {
        return commands.size();
    }

    public Iterable<DrawCommand> getCommands() {
        return commands;
    }
}
