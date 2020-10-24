package com.practice.online_diagnost.exceptions;

/**
 * An exception that provides information on a DAO error.
 *
 * @author Fazliddin Makhsudov
 */
public class RepositoryException extends AppException {

    private static final long serialVersionUID = -3550446897536410392L;

    public RepositoryException() {
    }

    /**
     * DAO exception.
     *
     * @param message message
     * @param cause   cause
     */
    public RepositoryException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
