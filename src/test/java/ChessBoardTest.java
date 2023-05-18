import exceptions.KeyDoesNotExistException;
import exceptions.PositionTakenException;
import exceptions.TooManyQueensException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ChessBoardTest {

    @Test
    public void addPawnShouldThrowTooManyQueensException() {
        //given
        final String expectedMessage = "Attempt to put too many Queens on board. Limit for board [ 5 ]";
        final ChessBoard chessBoard = new ChessBoard(5);
        chessBoard.setInitialPawn(0, 0);
        chessBoard.addPawn(0, 1);
        chessBoard.addPawn(0, 2);
        chessBoard.addPawn(0, 3);
        chessBoard.addPawn(0, 4);

        //when
        final Exception exception = assertThrows(TooManyQueensException.class,
                                                 () -> chessBoard.addPawn(1, 0));

        //then
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void addPawnShouldThrowPositionTakenException() {
        //given
        final String expectedMessage = "The place you tried to set the pawn [ 0, 0 ] is already occupied";
        final ChessBoard chessBoard = new ChessBoard(5);
        chessBoard.setInitialPawn(0, 0);

        //when
        final Exception exception = assertThrows(PositionTakenException.class,
                                                 () -> chessBoard.addPawn(0, 0));

        //then
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void setPawnShouldThrowPositionTakenException() {
        //given
        final String expectedMessage = "The place you tried to set the pawn [ 0, 0 ] is already occupied";
        final ChessBoard chessBoard = new ChessBoard(5);
        chessBoard.setInitialPawn(0, 0);
        chessBoard.addPawn(0, 1);

        //when
        final Exception exception = assertThrows(PositionTakenException.class,
                                                 () -> chessBoard.setPawn(1, 0, 0));

        //then
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void setPawnShouldThrowKeyDoesNotExistException() {
        //given
        final String expectedMessage = "The pawn you tried to assign values to [ 1 ] doesnt exist";
        final ChessBoard chessBoard = new ChessBoard(5);

        //when
        final Exception exception = assertThrows(KeyDoesNotExistException.class,
                                                 () -> chessBoard.setPawn(1, 0, 0));

        //then
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void countShouldReturnCorrectNumberOfAttackingPawns() {
        //given
        final int expectedCount = 0;
        final ChessBoard chessBoard = new ChessBoard(4);
        chessBoard.setInitialPawn(2, 0);
        chessBoard.addPawn(3, 2);
        chessBoard.addPawn(1, 3);
        chessBoard.addPawn(0, 1);

        final AttackingPawsCounter attackingPawsCounter = mock(AttackingPawsCounter.class);
        when(attackingPawsCounter.count(chessBoard)).thenReturn(0);

        //when
        final int count = attackingPawsCounter.count(chessBoard);

        //then
        assertEquals(expectedCount, count);
    }
}
