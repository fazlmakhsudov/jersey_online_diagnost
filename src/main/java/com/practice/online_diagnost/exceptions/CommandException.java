package com.practice.online_diagnost.exceptions;

/**
 * An exception that provides information on a Command layer.
 *
 * @author Fazliddin Makhsudov
 */
public class CommandException extends RuntimeException {

    private static final long serialVersionUID = -3550446897536410392L;

    public CommandException() {
    }

    /**
     * service exception.
     *
     * @param message message
     * @param cause   cause
     */
    public CommandException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
