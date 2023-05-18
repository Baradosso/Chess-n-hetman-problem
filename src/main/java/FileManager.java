import java.io.*;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FileManager {
    public static ChessBoard getDataFromFile(final String path) {
        final ChessBoard chessBoard = new ChessBoard();
        final Map<Integer, QueenPawn> queenPawns = new HashMap<>();

        try (final Scanner scanner = new Scanner(new File(path))) {
            if (scanner.hasNextLine()) {
                final String data = scanner.nextLine();
                chessBoard.setBoardSize(Integer.parseInt(data));
            }

            while (scanner.hasNextLine()) {
                final String data = scanner.nextLine();

                final String[] token = data.split(";");
                final QueenPawn queenPawn = new QueenPawn(Integer.parseInt(token[1]),
                                                          Integer.parseInt(token[2]));
                queenPawns.put(Integer.parseInt(token[0]), queenPawn);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        chessBoard.setQueenPawns(queenPawns);
        return chessBoard;
    }

    public static void saveDataToFile(final int boardSize,
                                      final Map<Integer, QueenPawn> queenPawns,
                                      final String path) {
        try (final BufferedWriter bufferedReader = new BufferedWriter(new FileWriter(path))) {

            bufferedReader.write(boardSize + System.lineSeparator());
            for (final int key : queenPawns.keySet()) {
                final QueenPawn queenPawn = queenPawns.get(key);
                bufferedReader.write(fileDataSet(key,
                                                 queenPawn.getPositionX(),
                                                 queenPawn.getPositionY()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String fileDataSet(final int key,
                               final int positionX,
                               final int positionY) {
        return MessageFormat.format("{0};{1};{2}{3}",
                                    key,
                                    positionX,
                                    positionY,
                                    System.lineSeparator());
    }
}
