public class App {
    public static void main(String[] args) throws Exception {
        InputChecker checker = new InputChecker();

        System.out.println("Game START!!");
        System.out.println("Please enter the size of the board, n");
        int boardSize = checker.catchIntInput();

        System.out.println("Please enter the number of mines, m");
        int numberOfMines = checker.catchIntInput();

        int maxMines = boardSize * boardSize - 1;
        while (numberOfMines > maxMines || numberOfMines < 1) {
            System.out.println("The number of mines is out of range. Between 1~" + maxMines);
            numberOfMines = checker.catchIntInput();
        }

        System.out.println("Would you like to enable the automatic mining feature?");
        System.out.println("(Automatically mine if there are no mines in the surrounding 8 squares)");
        System.out.println("Yes: Y or No: N");
        boolean autDigFlag = checker.catchRightInput();

        Board board = new Board(boardSize);
        Operation operation = new Operation(autDigFlag);

        // ゲームの初期化
        

        // ゲームプレイ
        // xxx
    }
}
