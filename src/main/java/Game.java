import exceptions.KeyDoesNotExistException;
import exceptions.PositionTakenException;
import exceptions.TooManyQueensException;
import lombok.Getter;

@Getter
public class Game {
    private final CustomScanner customScanner;
    private final AttackingPawnsCounter attackingPawnsCounter;
    private ChessBoard chessBoard;

    public Game(final CustomScanner customScanner, final AttackingPawnsCounter attackingPawnsCounter) {
        this.customScanner = customScanner;
        this.attackingPawnsCounter = attackingPawnsCounter;
        this.chessBoard = new ChessBoard(customScanner.getIntInput());
    }

    public void loadGame() {
        chessBoard = FileManager.getDataFromFile(customScanner.getStringInput());
    }

    public void newGame() {
        try {
            chessBoard.setInitialPawn(customScanner.getIntInput(),
                                      customScanner.getIntInput());
        } catch (PositionTakenException e) {
            e.printStackTrace();
        }

        while (chessBoard.getQueenPawns().size() < chessBoard.getBoardSize()) {
            try {
                chessBoard.addPawn(customScanner.getIntInput(),
                                   customScanner.getIntInput());
            } catch (TooManyQueensException | PositionTakenException e) {
                e.printStackTrace();
            }
        }

        while (attackingPawnsCounter.count(chessBoard) != 0) {
            if (customScanner.getIntInput() == 1) {
                FileManager.saveDataToFile(chessBoard.getBoardSize(),
                                           chessBoard.getQueenPawns(),
                                           customScanner.getStringInput());
            }

            if (customScanner.getIntInput() == 1) {
                break;
            }

            try {
                chessBoard.setPawn(customScanner.getIntInput(),
                                   customScanner.getIntInput(),
                                   customScanner.getIntInput());
            } catch (KeyDoesNotExistException | PositionTakenException e) {
                e.printStackTrace();
            }
        }
    }
}
