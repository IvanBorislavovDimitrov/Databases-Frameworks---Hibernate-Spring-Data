import java.util.*;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String email = input.nextLine();
        String text = input.nextLine();

        String[] words = text.split("\\s+");
        for (int i = 0; i < words.length; i++) {
            if (email.equals(words[i])) {
                words[i] = getCensoredEmail(words[i]);
            }
        }

        System.out.println(String.join(" ", words));
    }

    private static String getCensoredEmail(String word) {
        String textToReplace = word.substring(0, word.indexOf("@"));
        String hiddenText = repeatString("*", textToReplace.length());
        return hiddenText + word.substring(word.indexOf("@"));

    }

    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("*");
        }

        return sb.toString();
    }
}
