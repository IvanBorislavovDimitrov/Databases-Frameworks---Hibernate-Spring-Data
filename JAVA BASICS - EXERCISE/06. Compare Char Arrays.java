import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String firstWord = input.nextLine();
        String secondWord = input.nextLine();
        boolean areEqual = true;

        for (int i = 0; i < Math.min(firstWord.length(), secondWord.length()); i++) {
            if (firstWord.charAt(i) < secondWord.charAt(i)) {
                areEqual = false;
                System.out.println(firstWord.replaceAll("\\s+", ""));
                System.out.println(secondWord.replaceAll("\\s+", ""));
                break;
            } else if (firstWord.charAt(i) > secondWord.charAt(i)) {
                areEqual = false;
                System.out.println(secondWord.replaceAll("\\s+", ""));
                System.out.println(firstWord.replaceAll("\\s+", ""));
                break;
            }
        }

        if (areEqual) {
            if (firstWord.length() > secondWord.length()) {
                System.out.println(secondWord.replaceAll("\\s+", ""));
                System.out.println(firstWord.replaceAll("\\s+", ""));
            } else {
                System.out.println(firstWord.replaceAll("\\s+", ""));
                System.out.println(secondWord.replaceAll("\\s+", ""));
            }
        }
    }
}
