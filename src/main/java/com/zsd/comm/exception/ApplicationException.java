package com.zsd.comm.exception;

import java.text.MessageFormat;

/**
 * 应用异常类.
 */
public class ApplicationException extends RuntimeException {

    public ApplicationException() {
        super();
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationException(Enum clazz, Throwable cause, Object... args) {
        this(MessageFormat.format(clazz.toString(), args), cause);
    }

    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(Enum clazz, Object... args) {
        super(MessageFormat.format(clazz.toString(), args));
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

}
