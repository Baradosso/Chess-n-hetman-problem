import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class GameTest {
    final CustomScanner customScanner = Mockito.mock(CustomScanner.class);
    final AttackingPawnsCounter attackingPawnsCounter = Mockito.mock(AttackingPawnsCounter.class);

    @Test
    public void shouldCorrectlyCreateGame() {
        //given
        final int expectedSize = 5;
        when(customScanner.getIntInput()).thenReturn(expectedSize);

        //when
        final Game game = new Game(customScanner, attackingPawnsCounter);

        //then
        assertEquals(expectedSize, game.getChessBoard().getBoardSize());
    }

    @Test
    public void shouldCorrectlyStartAndEndNewGame() {
        //given
        when(customScanner.getIntInput()).thenReturn(2)
                                         .thenReturn(0).thenReturn(0)
                                         .thenReturn(0).thenReturn(1)
                                         .thenReturn(0)
                                         .thenReturn(1);
        final Game game = new Game(customScanner, attackingPawnsCounter);

        //when
        game.newGame();

        //then
        final Map<Integer, QueenPawn> queenPawnMap = game.getChessBoard().getQueenPawns();

        assertEquals(new QueenPawn(0, 0), queenPawnMap.get(0));
        assertEquals(new QueenPawn(0, 1), queenPawnMap.get(1));
        assertEquals(2, game.getChessBoard().getBoardSize());
    }
}
