import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Board {

    private int boardSize;
    private int mines;
    private Cell[][] boardInfo;
    private boolean[][] visited;

    public Board(int boardSize) {
        this.boardSize = boardSize;

        this.boardInfo = new Cell[boardSize][boardSize];
        this.visited = new boolean[boardSize][boardSize];

        for (int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                this.boardInfo[i][j] = new Cell();
                this.visited[i][j] = false;
            }
        }
    }

    public void putMines(int mines) {
        this.mines = mines;
        Random rand = new Random();
        int placedMines = 0;
        Set<String> mineCoordinates = new HashSet<>();

        while (placedMines < mines) {
            int x = rand.nextInt(boardSize);
            int y = rand.nextInt(boardSize);
            String coord = x + "," + y;

            if (!mineCoordinates.contains(coord) &&
                    !boardInfo[x][y].hasMine() && boardInfo[x][y].hasDug()) {
                boardInfo[x][y].setMine();
                mineCoordinates.add(coord);
                placedMines++;
            }
        }
    }

    public int calculateMineCount(int row, int col) {
        int mineCount = 0;
        final int[][] DIRECTIONS = {
                {-1, 0}, {-1, 1}, {-1, -1}, {0, 1}, {0, -1}, {1, 0}, {1, 1}, {1, -1}
        };

        for (int[] direction : DIRECTIONS) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];
            if (newRow >= 0 && newRow < boardInfo.length && newCol >= 0
                    && newCol < boardInfo[row].length && boardInfo[newRow][newCol].hasMine()) {
                mineCount++;
            }
        }
        return mineCount;
    }

}
