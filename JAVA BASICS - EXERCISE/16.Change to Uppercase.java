import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String regex = "<upcase>(.*?)<\\/upcase>";
        String text = input.nextLine();

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String toUp = matcher.group(1);
            text = text.replaceAll(matcher.group(), toUp.toUpperCase());
        }

        System.out.println(text);
    }
}
