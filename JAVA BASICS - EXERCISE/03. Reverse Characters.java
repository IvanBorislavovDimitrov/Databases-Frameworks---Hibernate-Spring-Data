import java.util.Collection;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char[] arr = new char[3];
        for (int i = 0; i < 3; i++) {
            arr[2 - i] = input.nextLine().charAt(0);
        }
        for (int i = 0; i < 3; i++) {
            System.out.printf("%c", arr[i]);
        }
        System.out.println();
    }
}
