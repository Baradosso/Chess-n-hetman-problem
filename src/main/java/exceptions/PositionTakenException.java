package exceptions;

import java.text.MessageFormat;

public class PositionTakenException extends RuntimeException {
    public PositionTakenException(final int positionX, final int positionY) {
        super(MessageFormat.format("The place you tried to set the pawn [ {0}, {1} ] is already occupied",
                                   positionX,
                                   positionY));
    }
}
