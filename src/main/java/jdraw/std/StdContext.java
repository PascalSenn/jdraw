/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */
package jdraw.std;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import jdraw.commands.AddGroupCommand;
import jdraw.commands.UngroupCommand;
import jdraw.figures.*;
import jdraw.figures.decorators.BorderDecorator;
import jdraw.figures.decorators.BundleDecorator;
import jdraw.framework.DrawCommandHandler;
import jdraw.framework.DrawModel;
import jdraw.framework.DrawTool;
import jdraw.framework.DrawToolFactory;
import jdraw.framework.DrawView;
import jdraw.framework.Figure;

import static java.util.stream.Collectors.toList;

/**
 * Standard implementation of interface DrawContext.
 *
 * @author Dominik Gruntz &amp; Christoph Denzler
 * @version 2.6, 24.09.09
 * @see DrawView
 */
@SuppressWarnings("serial")
public class StdContext extends AbstractContext {
    /**
     * Constructs a standard context with a default set of drawing tools.
     *
     * @param view the view that is displaying the actual drawing.
     */
    public StdContext(DrawView view) {
        super(view, null);
    }

    /**
     * Constructs a standard context. The drawing tools available can be
     * parameterized using <code>toolFactories</code>.
     *
     * @param view          the view that is displaying the actual drawing.
     * @param toolFactories a list of DrawToolFactories that are available to the user
     */
    public StdContext(DrawView view, List<DrawToolFactory> toolFactories) {
        super(view, toolFactories);
    }

    /**
     * Creates and initializes the "Edit" menu.
     *
     * @return the new "Edit" menu.
     */
    @Override
    protected JMenu createEditMenu() {
        JMenu editMenu = new JMenu("Edit");
        final JMenuItem undo = new JMenuItem("Undo");
        undo.setAccelerator(KeyStroke.getKeyStroke("control Z"));
        editMenu.add(undo);
        undo.addActionListener(e -> {
                    final DrawCommandHandler h = getModel().getDrawCommandHandler();
                    if (h.undoPossible()) {
                        h.undo();
                    }
                }
        );

        final JMenuItem redo = new JMenuItem("Redo");
        redo.setAccelerator(KeyStroke.getKeyStroke("control Y"));
        editMenu.add(redo);
        redo.addActionListener(e -> {
                    final DrawCommandHandler h = getModel().getDrawCommandHandler();
                    if (h.redoPossible()) {
                        h.redo();
                    }
                }
        );
        editMenu.addSeparator();

        JMenuItem sa = new JMenuItem("SelectAll");
        sa.setAccelerator(KeyStroke.getKeyStroke("control A"));
        editMenu.add(sa);
        sa.addActionListener(e -> {
                    getModel().getFigures().forEachOrdered(f -> getView().addToSelection(f));
                    getView().repaint();
                }
        );

        editMenu.addSeparator();
        editMenu.add("Cut").addActionListener(e -> {
            cut(getView());
        });
        editMenu.add("Copy").addActionListener(e -> {
            copy(getView());
        });
        editMenu.add("Paste").addActionListener(e -> {
            paste(getView());
        });

        editMenu.addSeparator();
        JMenuItem clear = new JMenuItem("Clear");
        editMenu.add(clear);
        clear.addActionListener(e -> {
            getModel().removeAllFigures();
        });

        editMenu.addSeparator();
        JMenuItem group = new JMenuItem("Group");
        group.addActionListener(e -> {
            group(getView());
        });
        editMenu.add(group);

        JMenuItem ungroup = new JMenuItem("Ungroup");
        ungroup.addActionListener(e -> {
            ungroup(getView());
        });
        editMenu.add(ungroup);

        editMenu.addSeparator();

        JMenu orderMenu = new JMenu("Order...");
        JMenuItem frontItem = new JMenuItem("Bring To Front");
        frontItem.addActionListener(e -> {
            bringToFront(getView().getModel(), getView().getSelection());
        });
        orderMenu.add(frontItem);
        JMenuItem backItem = new JMenuItem("Send To Back");
        backItem.addActionListener(e -> {
            sendToBack(getView().getModel(), getView().getSelection());
        });
        orderMenu.add(backItem);
        editMenu.add(orderMenu);

        JMenu grid = new JMenu("Grid...");

        JMenuItem basicGrid = new JMenuItem("BasicGrid");
        basicGrid.addActionListener((e) -> getView().setGrid(new BasicGrid()));
        JMenuItem snapGrid = new JMenuItem("SnapGrid");
        snapGrid.addActionListener((e) -> getView().setGrid(new SnapGrid()));
        JMenuItem noGrid = new JMenuItem("No Grid");
        noGrid.addActionListener((e) -> getView().setGrid(null));
        JMenu decsMenu = new JMenu("Decorators...");

        editMenu.add(decsMenu);
        JMenuItem addBorder = new JMenuItem("Add Border Decorator");
        decsMenu.add(addBorder);
        addBorder.addActionListener(e -> {
            List<Figure> s = getView().getSelection();
            getView().clearSelection();
            for (Figure f : s) {
                BorderDecorator dec = new BorderDecorator(f);
                getModel().removeFigure(f);
                getModel().addFigure(dec);
                getView().addToSelection(dec);
            }
        });
        JMenuItem addBundle = new JMenuItem("Add Bundle Decorator");
        decsMenu.add(addBundle);
        addBundle.addActionListener(e -> {
            List<Figure> s = getView().getSelection();
            getView().clearSelection();
            for (Figure f : s) {
                BundleDecorator dec = new BundleDecorator(f);
                getModel().removeFigure(f);
                getModel().addFigure(dec);
                getView().addToSelection(dec);
            }
        });
        grid.add(noGrid);
        grid.add(snapGrid);
        grid.add(basicGrid);
        editMenu.add(grid);

        return editMenu;
    }

    /**
     * Creates and initializes items in the file menu.
     *
     * @return the new "File" menu.
     */
    @Override
    protected JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        fileMenu.add(open);
        open.setAccelerator(KeyStroke.getKeyStroke("control O"));
        open.addActionListener(e -> doOpen());

        JMenuItem save = new JMenuItem("Save");
        save.setAccelerator(KeyStroke.getKeyStroke("control S"));
        fileMenu.add(save);
        save.addActionListener(e -> doSave());

        JMenuItem exit = new JMenuItem("Exit");
        fileMenu.add(exit);
        exit.addActionListener(e -> System.exit(0));

        return fileMenu;
    }

    @Override
    protected void doRegisterDrawTools() {
        // TODO Add new figure tools here
        DrawTool rectangleTool = new RectTool(this);
        addTool(rectangleTool);
        addTool(new CircleTool(this));
        addTool(new OvalTool(this));
        addTool(new LineTool(this));
    }

    /**
     * Changes the order of figures and moves the figures in the selection
     * to the front, i.e. moves them to the end of the list of figures.
     *
     * @param model     model in which the order has to be changed
     * @param selection selection which is moved to front
     */
    public void bringToFront(DrawModel model, List<Figure> selection) {
        // the figures in the selection are ordered according to the order in the model
        List<Figure> orderedSelection = model.getFigures().filter(f -> selection.contains(f)).collect(toList());
        Collections.reverse(orderedSelection);
        int pos = (int) model.getFigures().count();
        for (Figure f : orderedSelection) {
            model.setFigureIndex(f, --pos);
        }
    }


    /**
     * Changes the order of figures and moves the figures in the selection
     * to the back, i.e. moves them to the front of the list of figures.
     *
     * @param model     model in which the order has to be changed
     * @param selection selection which is moved to the back
     */
    public void sendToBack(DrawModel model, List<Figure> selection) {
        // the figures in the selection are ordered according to the order in the model
        List<Figure> orderedSelection = model.getFigures().filter(f -> selection.contains(f)).collect(toList());
        int pos = 0;
        for (Figure f : orderedSelection) {
            model.setFigureIndex(f, pos++);
        }
    }

    public void group(DrawView view) {
        var model = view.getModel();
        var selection = view.getSelection();
        var group = new Group(selection, model);
        var cmd = new AddGroupCommand(group, view, model);
        model.getDrawCommandHandler().addCommand(cmd);
        cmd.redo();
    }


    public void ungroup(DrawView view) {
        var model = view.getModel();
        var selection = view.getSelection();
        var groups = selection.stream()
                .filter(x -> x instanceof Group)
                .map(g -> ((Group) g))
                .collect(toList());

        groups.forEach(g -> {
            var cmd = new UngroupCommand(g, view, model);
            model.getDrawCommandHandler().addCommand(cmd);
            cmd.redo();
        });

    }


    /**
     * Handles the saving of a drawing to a file.
     */
    private void doSave() {
        JFileChooser chooser = new JFileChooser(getClass().getResource("").getFile());
        chooser.setDialogTitle("Save Graphic");
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);

        chooser.setFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.draw)", "draw"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.xml)", "xml"));
        chooser.addChoosableFileFilter(new FileNameExtensionFilter("JDraw Graphics (*.json)", "json"));

        int res = chooser.showSaveDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            // save graphic
            File file = chooser.getSelectedFile();
            FileFilter filter = chooser.getFileFilter();
            if (filter instanceof FileNameExtensionFilter && !filter.accept(file)) {
                file = new File(chooser.getCurrentDirectory(), file.getName() + "." + ((FileNameExtensionFilter) filter).getExtensions()[0]);
            }


            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject((Serializable) getModel().getFigures().map(Figure::clone).collect(Collectors.toList()));
            } catch (Exception ex) {
                throw new RuntimeException((ex.getMessage()));
            }
        }
    }

    /**
     * Handles the opening of a new drawing from a file.
     */
    private void doOpen() {
        JFileChooser chooser = new JFileChooser(getClass().getResource("")
                .getFile());
        chooser.setDialogTitle("Open Graphic");
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public String getDescription() {
                return "JDraw Graphic (*.draw)";
            }

            @Override
            public boolean accept(File f) {
                return f.isDirectory() || f.getName().endsWith(".draw");
            }
        });
        int res = chooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            var path = chooser.getSelectedFile().getAbsolutePath();
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
                getModel().removeAllFigures();
                var figures = (List<Figure>) ois.readObject();
                for (var f : figures) {
                    getView().getModel().addFigure(f);
                }
            } catch (Exception ex) {
                throw new RuntimeException((ex.getMessage()));
            }
        }
    }

    private List<Figure> clipboard;

    private void cut(DrawView view) {
        if (view.getSelection().size() > 0) {
            clipboard = view.getSelection();
            var model = view.getModel();
            view.getSelection().stream().forEach(f -> {
                        view.removeFromSelection(f);
                        model.removeFigure(f);
                    }
            );
        }
    }

    private void copy(DrawView view) {
        // clone on copy to keep the original reference (position e.g.)
        if (view.getSelection().size() > 0) {
            clipboard = view
                    .getSelection()
                    .stream()
                    .map(Figure::clone)
                    .collect(Collectors.toUnmodifiableList());
        }
    }

    private void paste(DrawView view) {
        if (clipboard != null && clipboard.size() > 0) {
            // clone on paste to keep the original reference
            var model = view.getModel();
            clipboard.stream()
                    .map(Figure::clone)
                    .forEach(f -> {
                                model.addFigure(f);
                                view.addToSelection(f);
                            }
                    );
        }


    }


}
