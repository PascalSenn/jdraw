package jdraw.commands;

import jdraw.figures.Group;
import jdraw.framework.DrawCommand;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import java.util.List;
import java.util.stream.Collectors;

public class UngroupCommand extends AddGroupCommand {

    public UngroupCommand(Group g, DrawView view, DrawModel model) {
        super(g, view, model);
    }

    @Override
    public void redo() {
        super.undo();
    }

    @Override
    public void undo() {
        super.redo();
    }


}
