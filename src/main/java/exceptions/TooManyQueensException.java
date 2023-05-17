package exceptions;

import java.text.MessageFormat;

public class TooManyQueensException extends RuntimeException {
    public TooManyQueensException(final int pawnsLimit) {
        super(MessageFormat.format("Attempt to put too many Queens on board. Limit for board [ {0} ]", pawnsLimit));
    }
}
