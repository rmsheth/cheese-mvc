package org.launchcode.models;

/**
 * Created by Andrew Bell on 3/30/17.
 */
public class InvalidUserException extends RuntimeException {

    // Neither of these constructors provide additional details, but I wanted to show
    // two common ways of creating an exception
    public InvalidUserException(String message) {
        super(message);
    }

    // This constructor allows us to pass in a message, as well as another exception
    // This is used if you want to wrap another exception in a try-catch and re-throw
    // with your own custom exception
    public InvalidUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
