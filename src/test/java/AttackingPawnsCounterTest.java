import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AttackingPawnsCounterTest {
    final AttackingPawnsCounter attackingPawnsCounter = mock(AttackingPawnsCounter.class);

    @Test
    public void countShouldReturnCorrectNumberOfAttackingPawns() {
        //given
        final int expectedCount = 0;
        final ChessBoard chessBoard = new ChessBoard(4);
        chessBoard.setInitialPawn(2, 0);
        chessBoard.addPawn(3, 2);
        chessBoard.addPawn(1, 3);
        chessBoard.addPawn(0, 1);
        when(attackingPawnsCounter.count(chessBoard)).thenReturn(0);

        //when
        final int count = attackingPawnsCounter.count(chessBoard);

        //then
        assertEquals(expectedCount, count);
    }
}
