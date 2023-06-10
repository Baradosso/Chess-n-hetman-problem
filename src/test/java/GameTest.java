import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GameTest {
    private final CustomScanner customScannerMocked = Mockito.mock(CustomScanner.class);
    private final static AttackingPawnsCounter attackingPawnsCounterMocked = Mockito.mock(AttackingPawnsCounter.class);
    private final CustomScanner customScanner = new CustomScanner();
    private final AttackingPawnsCounter attackingPawnsCounter = new AttackingPawnsCounter();
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeAll
    public static void init() {
        when(attackingPawnsCounterMocked.count(any(ChessBoard.class))).thenReturn(1);
    }

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(System.out);
    }

    @Test
    public void shouldCorrectlyCreateGame() {
        //given
        final int expectedSize = 5;
        when(customScannerMocked.getIntInput()).thenReturn(expectedSize);

        //when
        final Game game = new Game(customScannerMocked, attackingPawnsCounterMocked);

        //then
        assertEquals(expectedSize, game.getChessBoard().getBoardSize());
    }

    @Test
    public void shouldCorrectlyStartAndEndNewGame() {
        //given
        when(customScannerMocked.getIntInput()).thenReturn(2)
                                               .thenReturn(0).thenReturn(0)
                                               .thenReturn(0).thenReturn(1)
                                               .thenReturn(0)
                                               .thenReturn(1);
        final Game game = new Game(customScannerMocked, attackingPawnsCounterMocked);

        //when
        game.newGame();

        //then
        final Map<Integer, QueenPawn> queenPawnMap = game.getChessBoard().getQueenPawns();

        assertEquals(new QueenPawn(0, 0), queenPawnMap.get(0));
        assertEquals(new QueenPawn(0, 1), queenPawnMap.get(1));
        assertEquals(2, game.getChessBoard().getBoardSize());
        assertEquals("GAME ENDED", outputStreamCaptor.toString().trim());
    }

    @Test
    public void shouldCorrectlySaveNewGame() {
        //given
        when(customScannerMocked.getIntInput()).thenReturn(4)
                                               .thenReturn(0).thenReturn(0)
                                               .thenReturn(0).thenReturn(1)
                                               .thenReturn(0).thenReturn(2)
                                               .thenReturn(0).thenReturn(3)
                                               .thenReturn(1)
                                               .thenReturn(1);

        final Game game1 = new Game(customScannerMocked, attackingPawnsCounterMocked);
        game1.newGame();
        final Game game2 = new Game(customScannerMocked, attackingPawnsCounterMocked);

        //when
        game2.loadGame();

        //then
        final Map<Integer, QueenPawn> queenPawnMap = game2.getChessBoard().getQueenPawns();

        assertEquals(new QueenPawn(0, 0), queenPawnMap.get(0));
        assertEquals(new QueenPawn(0, 1), queenPawnMap.get(1));
        assertEquals(new QueenPawn(0, 2), queenPawnMap.get(2));
        assertEquals(new QueenPawn(0, 3), queenPawnMap.get(3));
        assertEquals(4, game2.getChessBoard().getBoardSize());
    }

    @Test
    public void shouldWinGame() {
        //given
        final Game game = new Game(customScanner, attackingPawnsCounter);

        //when
        game.newGame();

        //then
        assertEquals("YOU WON", outputStreamCaptor.toString().trim());
    }
}
