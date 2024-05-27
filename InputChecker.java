import java.util.Scanner;

public class InputChecker {

    public int catchIntInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            if (sc.hasNextInt()) {
                int input = sc.nextInt();
                sc.nextLine(); // 次の入力操作が新しい行から始まるようにする
                sc.close();
                return input;
            } else {
                System.out.println("Invalid input. Please input an integer.");
                sc.next();
            }
        }
    }

    public boolean catchRightInput() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String Input = sc.nextLine().trim().toUpperCase();
            if (Input.equals("Y")) {
                sc.close();
                return true;
            } else if (Input.equals("N")) {
                sc.close();
                return false;
            } else {
                System.out.println("Invalid input. Please input a valid value. Y or N");
            }
        }
    }

}
