import java.util.Collection;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        char ch = input.nextLine().charAt(0);
        if (Character.isDigit(ch)) {
            System.out.println("digit");
        } else if (isVowel(ch)) {
            System.out.println("vowel");
        } else {
            System.out.println("other");
        }
     }

    private static boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'o' || ch == 'i' || ch == 'u') {
            return true;
        }

        return false;
    }
}
