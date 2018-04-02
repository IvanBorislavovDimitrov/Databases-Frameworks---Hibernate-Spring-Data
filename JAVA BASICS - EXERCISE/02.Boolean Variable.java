import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String answer = input.nextLine().equalsIgnoreCase("true") ? "Yes" : "No";
        System.out.println(answer);
    }
}
