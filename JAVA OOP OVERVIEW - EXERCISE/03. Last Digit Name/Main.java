package last_digit_number;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m = Integer.parseInt(input.nextLine()) % 10;
        Number number = new Number();
        number.tellDigit(m);
    }
}
