import exceptions.KeyDoesNotExistException;
import exceptions.PositionTakenException;
import exceptions.TooManyQueensException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class ChessBoard {
    private int boardSize;
    private Map<Integer, QueenPawn> queenPawns = new HashMap<>();

    public ChessBoard(final int boardSize) {
        this.boardSize = boardSize;
    }

    public void setInitialPawn(final int positionX,
                               final int positionY) {
        final QueenPawn pawn = new QueenPawn(boardSize,
                                             positionX,
                                             positionY);
        queenPawns.put(0, pawn);
    }

    public void addPawn(final int positionX,
                        final int positionY) {
        if (queenPawns.size() >= boardSize) {
            throw new TooManyQueensException(boardSize - 1);
        }
        final QueenPawn pawn = new QueenPawn(positionX,
                                            positionY);
        queenPawns.put(queenPawns.size(), pawn);
    }

    public void setPawn(final int key,
                        final int positionX,
                        final int positionY) {
        if (!queenPawns.containsKey(key)) {
            throw new KeyDoesNotExistException(key);
        }

        if (isPositionTaken(positionX, positionY)) {
            throw new PositionTakenException(positionX, positionY);
        }

        queenPawns.get(key).setPositionX(positionX);
        queenPawns.get(key).setPositionY(positionY);
    }

    public int count() {
        return 0;
    }

    private boolean isPositionTaken(final int positionX,
                                    final int positionY) {
        for (final QueenPawn pawn : queenPawns.values()) {
            if (pawn.getPositionX() == positionX &&
                pawn.getPositionY() == positionY) {
                return true;
            }
        }
        return false;
    }
}
