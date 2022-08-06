package com.amdevelopment.NetCracker_Project.config.exceptions;

public class DateFormatException extends IllegalArgumentException{

    /**
     * Constructs an <code>IllegalArgumentException</code> with no
     * detail message.
     */
    public DateFormatException() {
    }

    /**
     * Constructs an <code>IllegalArgumentException</code> with the
     * specified detail message.
     *
     * @param s the detail message.
     */
    public DateFormatException(String s) {
        super(s);
    }
}
