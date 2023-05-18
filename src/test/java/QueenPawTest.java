import exceptions.OutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueenPawTest {

    @Test
    public void shouldCreateNewQueenPawn() {
        //given

        //when

        //then
        assertDoesNotThrow(() -> new QueenPawn(0, 2, 5));
    }

    @Test
    public void shouldThrowOutOfBoundsException() {
        //given
        final String expectedMessage = "Attempt to set Queens pawn [ 6 ] out of bounds of the board: [ 0, 4 ]";
        final QueenPawn queenPawn = new QueenPawn(0, 0, 5);

        //when
        final Exception exception = assertThrows(OutOfBoundsException.class,
                                                 () -> queenPawn.setPositionX(6));

        //then
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    public void shouldNotAttack() {
        //given
        final QueenPawn queenPawn1 = new QueenPawn(0, 0, 5);
        final QueenPawn queenPawn2 = new QueenPawn(2, 1);

        //when
        final boolean isAttacking = queenPawn1.isAttackingPawn(queenPawn2);

        //then
        assertFalse(isAttacking);
    }

    @Test
    public void shouldAttack() {
        //given
        final QueenPawn queenPawn1 = new QueenPawn(0, 0, 5);
        final QueenPawn queenPawn2 = new QueenPawn(2, 2);

        //when
        final boolean isAttacking = queenPawn1.isAttackingPawn(queenPawn2);

        //then
        assertTrue(isAttacking);
    }
}
