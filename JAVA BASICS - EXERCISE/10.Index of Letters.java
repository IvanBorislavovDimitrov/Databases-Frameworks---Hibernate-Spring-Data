import java.util.*;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String word = input.nextLine();
        String[] lettersCount = new String[word.length()];

        for (int i = 0; i < word.length(); i++) {
            lettersCount[i] = word.charAt(i) + " -> " + getCountOfLetter(word.charAt(i));
        }

        for (String cnt : lettersCount) {
            System.out.println(cnt);
        }
    }

    private static int getCountOfLetter(char ch) {
        return ch - 'a';
    }
}
