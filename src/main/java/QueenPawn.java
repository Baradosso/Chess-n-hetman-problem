import exceptions.OutOfBoundsException;
import lombok.Getter;

public class QueenPawn {
    private static int BOARD_SIZE;
    @Getter
    private int positionX;
    @Getter
    private int positionY;
    @Getter
    private boolean isAttacking;

    public QueenPawn(final int positionX,
                     final int positionY) {
        setPositionX(positionX);
        setPositionY(positionY);
    }

    public QueenPawn(final int positionX,
                     final int positionY,
                     final int boardSize) {
        try {
            BOARD_SIZE = boardSize;
            setPositionX(positionX);
            setPositionY(positionY);
        } catch (OutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void setPositionX(final int positionX) throws OutOfBoundsException {
        if ((positionX > BOARD_SIZE - 1) || (positionX < 0)) {
            throw new OutOfBoundsException(BOARD_SIZE - 1, positionX);
        }
        this.positionX = positionX;
    }

    public void setPositionY(final int positionY) throws OutOfBoundsException {
        if ((positionY > BOARD_SIZE - 1) || (positionY < 0)) {
            throw new OutOfBoundsException(BOARD_SIZE - 1, positionY);
        }
        this.positionY = positionY;
    }

    public boolean isAttackingPawn(final QueenPawn queenPawn) {
        isAttacking = isAttackingVerticallyOrHorizontally(queenPawn.positionX, queenPawn.positionY) ||
                      isAttackingDiagonally(queenPawn.positionX, queenPawn.positionY);
        return isAttacking;
    }

    private boolean isAttackingVerticallyOrHorizontally(final int positionX,
                                                        final int positionY) {
        return this.positionY == positionY ||
               this.positionX == positionX;
    }

    private boolean isAttackingDiagonally(final int positionX,
                                          final int positionY) {
        return isAttackingFromTopLeft(positionX, positionY) ||
               isAttackingFromTopRight(positionX, positionY);
    }

    private boolean isAttackingFromTopLeft(final int positionX,
                                           final int positionY) {
        final int minPositionValue = Math.min(positionX, positionY);
        int diagonalPositionX = this.positionX - minPositionValue;
        int diagonalPositionY = this.positionY - minPositionValue;

        while ((diagonalPositionX < BOARD_SIZE) || (diagonalPositionY < BOARD_SIZE)) {
            if (diagonalPositionX == positionX && diagonalPositionY == positionY) {
                return true;
            }
            diagonalPositionX++;
            diagonalPositionY++;
        }
        return false;
    }

    private boolean isAttackingFromTopRight(final int positionX,
                                            final int positionY) {

        final int minPositionValue = Math.min((BOARD_SIZE - 1) - this.positionX, positionY);
        int diagonalPositionX = this.positionX + minPositionValue;
        int diagonalPositionY = this.positionY - minPositionValue;

        while ((diagonalPositionX < BOARD_SIZE) || (diagonalPositionY >= 0)) {
            if (diagonalPositionX == positionX && diagonalPositionY == positionY) {
                return true;
            }
            diagonalPositionX++;
            diagonalPositionY--;
        }
        return false;
    }
}
