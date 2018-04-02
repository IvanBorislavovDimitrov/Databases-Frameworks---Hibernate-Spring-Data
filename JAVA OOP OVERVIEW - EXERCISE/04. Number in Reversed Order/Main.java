package decimal_number;

import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        DecimalNumber decimalNumber = new DecimalNumber();
        decimalNumber.printAllDigits(Double.parseDouble(input.nextLine()));
    }
}
