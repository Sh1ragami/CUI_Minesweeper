import java.util.Scanner;

public class InputChecker {

    private Board board;
    private Cell[][] boardInfo;
    private Scanner scanner;

    // エラーメッセージの定義
    private static final String ERROR_INVALID_INPUT = "エラー：有効な値を入力してください";
    private static final String ERROR_INVALID_COORDINATES = "エラー：有効な座標を入力してください";
    private static final String ERROR_CANNOT_DIG = "採掘不可：もう一度入力してください";
    private static final String ERROR_CANNOT_PLACE_FLAG = "フラグ設置不可：もう一度入力してください";

    public InputChecker() {
        this.scanner = new Scanner(System.in);
    }

    public void setBoard(Board board) {
        this.board = board;
        this.boardInfo = board.getBoardInfo();
    }

    public int catchIntInput() {
        int input = 0;

        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                scanner.nextLine(); // 次の入力操作が新しい行から始まるようにする
                break;
            } else {
                System.out.println("整数値を入力してください。");
                scanner.next();
            }
        }
        return input;
    }

    public void printControl() {
        boolean continueGame = true;

        while (continueGame) {
            System.out.println("\n操作: x座標(行) y座標(列) 操作コマンド (D: 採掘, F: フラグ設置, E: 終了)");
            String control = scanner.nextLine().toUpperCase();
            continueGame = fieldChange(control);
        }
    }

    // 初回採掘時の操作を受け付ける
    public void initialDig() {
        boolean validInput = false;

        while (!validInput) {
            System.out.println("\n操作: x座標(行) y座標(列)");
            String control = scanner.nextLine();
            control += " D";
            validInput = !fieldChange(control);
        }
    }

    private boolean fieldChange(String control) {
        String[] controlData = control.split(" ");
        if (controlData.length != 3) {
            System.out.println(ERROR_INVALID_INPUT);
            return true;
        }

        try {
            int row = Integer.parseInt(controlData[0]) - 1;
            int col = Integer.parseInt(controlData[1]) - 1;
            String action = controlData[2];

            if (!(row >= 0 && row < boardInfo.length && col >= 0 && col < boardInfo[row].length)) {
                System.out.println(ERROR_INVALID_COORDINATES);
                return true;
            }

            return handleAction(row, col, action);

        } catch (NumberFormatException e) {
            System.out.println(ERROR_INVALID_INPUT);
            return true;
        }
    }

    // 操作コマンドを処理
    private boolean handleAction(int row, int col, String action) {
        Cell cell = boardInfo[row][col];
        switch (action) {
            case "D":
                return handleDigAction(cell);
            case "F":
                return handleFlagAction(cell);
            case "E":
                System.out.println("マインスイーパを終了します");
                System.exit(0);
                return false;
            default:
                System.out.println(ERROR_INVALID_INPUT);
                return true;
        }
    }

    // 採掘操作を処理
    private boolean handleDigAction(Cell cell) {
        if (!cell.isFlagged() && cell.hasMine()) {
            board.printEndField();
            return false;
        } else if (!cell.hasDug() && !cell.isFlagged()) {
            cell.setDug();
            return false;
        } else {
            System.out.println(ERROR_CANNOT_DIG);
            return true;
        }
    }

    // フラグ設置操作を処理
    private boolean handleFlagAction(Cell cell) {
        if (!cell.isFlagged() && !cell.hasDug()) {
            cell.setFlagged(true);
            return false;
        } else if (cell.isFlagged()) {
            cell.setFlagged(false);
            return false;
        } else {
            System.out.println(ERROR_CANNOT_PLACE_FLAG);
            return true;
        }
    }

}
