import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class FileManagerTest {

    private static final String PATH = "chess_data.txt";
    private File file;

    @BeforeEach
    public void init() {
        file = new File(PATH);
    }

    @AfterEach
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void teardown() {
        file.delete();
    }

    @Test
    public void shouldCorrectlySaveDataToFile() {
        //given
        final String expectedData = MessageFormat.format("4{0}" +
                                                         "0;0;0{0}" +
                                                         "1;0;1{0}" +
                                                         "2;0;2{0}" +
                                                         "3;0;3{0}",
                                                         System.lineSeparator());
        final int boardSize = 4;
        final Map<Integer, QueenPawn> queenPawns = new HashMap<>();
        queenPawns.put(0, new QueenPawn(0, 0, boardSize));
        queenPawns.put(1, new QueenPawn(0, 1));
        queenPawns.put(2, new QueenPawn(0, 2));
        queenPawns.put(3, new QueenPawn(0, 3));

        //when
        FileManager.saveDataToFile(boardSize,
                                   queenPawns,
                                   PATH);

        //then
        final String data = assertDoesNotThrow(this::readFileData);
        assertEquals(expectedData, data);
    }

    @Test
    public void shouldCorrectlyAssignVariables() {
        //given
        final int boardSize = 4;
        final Map<Integer, QueenPawn> queenPawns = new HashMap<>();
        queenPawns.put(0, new QueenPawn(0, 0, boardSize));
        queenPawns.put(1, new QueenPawn(0, 1));
        queenPawns.put(2, new QueenPawn(0, 2));
        queenPawns.put(3, new QueenPawn(0, 3));

        FileManager.saveDataToFile(boardSize,
                                   queenPawns,
                                   PATH);
        //when
        final ChessBoard chessBoard = assertDoesNotThrow(() -> FileManager.getDataFromFile(PATH));

        //then
        assertEquals(boardSize,
                     chessBoard.getBoardSize());
        assertTrue(comparePaws(queenPawns.get(0),
                               chessBoard.getQueenPawns().get(0)));
        assertTrue(comparePaws(queenPawns.get(1),
                               chessBoard.getQueenPawns().get(1)));
        assertTrue(comparePaws(queenPawns.get(2),
                               chessBoard.getQueenPawns().get(2)));
        assertTrue(comparePaws(queenPawns.get(3),
                               chessBoard.getQueenPawns().get(3)));
    }

    private String readFileData() throws FileNotFoundException {
        final Scanner scanner = new Scanner(new File(PATH));
        final StringBuilder stringBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            stringBuilder.append(scanner.nextLine())
                         .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    private boolean comparePaws(final QueenPawn queenPawn1,
                                final QueenPawn queenPawn2 ) {
        return queenPawn1.getPositionX() == queenPawn2.getPositionX() &&
               queenPawn1.getPositionY() == queenPawn2.getPositionY();
    }
}
