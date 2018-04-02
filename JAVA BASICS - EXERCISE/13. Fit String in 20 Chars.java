import java.util.*;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();

        if (str.length() < 20) {
            System.out.println(str + repeatString("*", 20 - str.length()));
        } else {
            System.out.println(str.substring(0, 20));
        }
    }

    private static String repeatString(String str, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append("*");
        }

        return sb.toString();
    }
}
