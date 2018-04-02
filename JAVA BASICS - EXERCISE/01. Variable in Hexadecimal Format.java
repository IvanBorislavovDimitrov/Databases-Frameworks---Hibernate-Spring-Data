import java.util.Scanner;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println(convertToDecimal(input.nextLine()));
    }

    private static long convertToDecimal(String hexadecimalNumber) {
        long number = 0;
        int power = 0;
        for (int i = hexadecimalNumber.length() - 1; i >= 0; i--) {
            int currentNumber = getCurrentNumber(hexadecimalNumber.charAt(i));
            number += currentNumber * Math.pow(16, power++);
        }

        return number;
    }

    private static int getCurrentNumber(char symbol) {
        if (Character.isDigit(symbol)) {
            return Integer.parseInt(String.valueOf(symbol));
        }
        switch (symbol) {
            case 'A':
                return 10;
            case 'B':
                return 11;
            case 'C':
                return 12;
            case 'D':
                return 13;
            case 'E':
                return 14;
            case 'F':
                return 15;
        }

        return 0;
    }
}
