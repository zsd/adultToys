package com.zsd.comm.exception;

/**
 * 数据访问异常类.
 */
public class DataAccessException extends ApplicationException {

    public DataAccessException() {
        super();
    }

    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataAccessException(Enum clazz, Throwable cause, Object... args) {
        super(clazz, cause, args);
    }

    public DataAccessException(String message) {
        super(message);
    }

    public DataAccessException(Enum clazz, Object... args) {
        super(clazz, args);
    }

    public DataAccessException(Throwable cause) {
        super(cause);
    }
}
