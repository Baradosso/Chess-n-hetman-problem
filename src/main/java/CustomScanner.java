public class CustomScanner {
    private int counter = 0;

    public String getStringInput() {
        return "chess.txt";
    }

    public int getIntInput() {
        final int[] returnsArray = {4,
                                    0, 1,
                                    1, 3,
                                    2, 0,
                                    3, 2,
                                    0};
        return returnsArray[counter++];
    }
}
