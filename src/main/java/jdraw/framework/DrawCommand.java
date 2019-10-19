/*
 * Copyright (c) 2018 Fachhochschule Nordwestschweiz (FHNW)
 * All Rights Reserved.
 */

package jdraw.framework;

import java.io.Serializable;

/**
 * The interface DrawCommand defines the functionality provided by
 * commands which can be stored in the command history.
 *
 * @author Dominik Gruntz &amp; Christoph Denzler
 * @version 2.5
 * @see DrawCommandHandler
 */

public interface DrawCommand extends Serializable {

    /**
     * Executes a command.
     */
    void redo();

    /**
     * Undoes the action performed by execute.
     */
    void undo();
}

