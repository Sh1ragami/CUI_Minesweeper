import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Board {

    private int boardSize;
    private int numberOfMines;
    private Cell[][] boardInfo;
    private final int[][] DIRECTIONS = {
            { -1, 0 }, { -1, 1 }, { -1, -1 }, { 0, 1 }, { 0, -1 }, { 1, 0 }, { 1, 1 }, { 1, -1 }
    };

    public Board(int boardSize) {
        this.boardSize = boardSize;
        this.boardInfo = new Cell[boardSize][boardSize];

        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                this.boardInfo[i][j] = new Cell();
            }
        }
    }

    public Cell[][] getBoardInfo() {
        return boardInfo;
    }

    public void putMines(int numberOfMines ) {
        this.numberOfMines = numberOfMines;
        Random rand = new Random();
        int placedMines = 0;
        Set<String> mineCoordinates = new HashSet<>();

        while (placedMines < numberOfMines) {
            int x = rand.nextInt(boardSize);
            int y = rand.nextInt(boardSize);
            String coord = x + "," + y;

            if (!mineCoordinates.contains(coord) && !boardInfo[x][y].hasMine() && !boardInfo[x][y].hasDug()) {
                boardInfo[x][y].setMine();
                mineCoordinates.add(coord);
                placedMines++;
            }
        }

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                boardInfo[i][j].setTotalMines(calculateMineCount(i, j));
            }
        }
    }

    private int calculateMineCount(int row, int col) {
        int mineCount = 0;

        for (int[] direction : DIRECTIONS) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow >= 0 && newRow < boardInfo.length && newCol >= 0 && newCol < boardInfo[row].length
                    && boardInfo[newRow][newCol].hasMine()) {
                mineCount++;
            }
        }
        return mineCount;
    }

    // フィールドの状態を出力
    public void printField() {
        printDecoration();
        printRowNumber();

        for (int i = 0; i < boardSize; i++) {
            // 行番号を表示
            System.out.printf("%2d ", i + 1); // 行番号の幅を2文字に固定

            for (int j = 0; j < boardSize; j++) {
                String output;
                if (boardInfo[i][j].isFlagged()) {
                    output = " F ";
                } else if (!boardInfo[i][j].hasDug()) {
                    output = " - ";
                } else if (boardInfo[i][j].hasMine()) {
                    output = " * ";
                } else {
                    if (boardInfo[i][j].getTotalMines() == 0) {
                        output = "   ";
                    } else {
                        output = String.format(" %d ", boardInfo[i][j].getTotalMines());
                    }
                }
                System.out.print(output);
            }
            System.out.println();
        }

        printDecoration();
    }

    public boolean checkClear() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (!boardInfo[i][j].hasMine() && !boardInfo[i][j].hasDug()) {
                    return false;
                }
            }
        }
        return true;
    }

    // ゲームオーバー時のフィールド出力
    public void printEndField() {
        System.out.println("");
        printDecoration();
        System.out.println("    <Game Over>");

        printRowNumber();

        for (int i = 0; i < boardSize; i++) {
            // 行番号を表示
            System.out.printf("%2d ", i + 1); // 行番号の幅を2文字に固定

            for (int j = 0; j < boardSize; j++) {
                String output;
                if (boardInfo[i][j].hasMine()) {
                    output = " * ";
                } else {
                    output = " - ";
                }
                System.out.print(output);
            }
            System.out.println();
        }
        printDecoration();
        System.out.println("");
        System.exit(0);
    }

    // ゲームの結果を出力
    public void printResult() {
        System.out.println("==========");
        System.out.println("<Game Clear>");
        System.out.println("=========");
        System.exit(0);
    }

    private void printDecoration() {
        for (int i = 0; i <= boardSize; i++) {
            System.out.print("---");
        }
        System.out.println();
    }

    private void printRowNumber() {
        System.out.print("  "); // 行番号と列番号の間に空白を入れる
        for (int i = 0; i < boardSize; i++) {
            System.out.printf("%3d", i + 1); // 列番号の幅を3文字に固定
        }
        System.out.println();
    }
}
