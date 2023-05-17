package exceptions;

import java.text.MessageFormat;

public class OutOfBoundsException extends RuntimeException {

    public OutOfBoundsException(final int boardSize, final int position) {
        super(MessageFormat.format("Attempt to set Queens pawn [ {0} ] out of bounds of the board: [ {1}, {2} ]",
                                   position,
                                   0,
                                   boardSize));
    }
}
