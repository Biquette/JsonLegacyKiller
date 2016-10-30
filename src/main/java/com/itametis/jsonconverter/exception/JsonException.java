package com.itametis.jsonconverter.exception;

/**
 *
 * @author <a href="mailto:chloe.mahalin@itametis.com">Chloé MAHALIN - ITAMETIS</a>
 */
public class JsonException extends Exception {

    /**
     * Display an exception from another exception.
     *
     * @param message the message
     * @param ex      the old exception.
     */
    public JsonException(String message, Exception ex) {
        super(message, ex);
    }


    /**
     * Display an exception.
     * Param will be substituted with {}.
     *
     * @param message the message
     * @param param   parameter to be substituted in the exception.
     */
    public JsonException(String message) {
        super(message);
    }

}
