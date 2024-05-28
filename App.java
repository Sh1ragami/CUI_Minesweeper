public class App {
    public static void main(String[] args) throws Exception {
        InputChecker checker = new InputChecker();

        System.out.println("Game START!!");
        System.out.println("フィールドの大きさnを入力 (n * n)");
        int boardSize = checker.catchIntInput();
        int maxMines = boardSize * boardSize - 1;

        System.out.println("地雷の数を入力 1~" + maxMines);
        int numberOfMines = checker.catchIntInput();

        while (numberOfMines > maxMines || numberOfMines < 1) {
            System.out.println("地雷数が範囲外です。 1~" + maxMines);
            numberOfMines = checker.catchIntInput();
        };

        Board board = new Board(boardSize);
        checker.setBoard(board);
        int digCount = 0;

        // ゲームの初期化
        board.printField();
        checker.initialDig();
        digCount++;
        board.putMines(numberOfMines);
        board.printField();

        // ゲームプレイ
        while (true) {
            // フィールドのマス数-地雷数の採掘後クリア判定開始
            if (digCount >= boardSize - numberOfMines) {
                boolean checkClear = board.checkClear();
                if (checkClear) {
                    board.printResult();
                }
            }
            checker.printControl();
            digCount++;
            board.printField();
        }
    }
}
