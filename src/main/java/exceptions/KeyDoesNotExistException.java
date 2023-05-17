package exceptions;

import java.text.MessageFormat;

public class KeyDoesNotExistException extends RuntimeException {
    public KeyDoesNotExistException(final int key) {
        super(MessageFormat.format("The pawn you tried to assign values to [ {0} ] doesn't exist",
                                   key));
    }
}
