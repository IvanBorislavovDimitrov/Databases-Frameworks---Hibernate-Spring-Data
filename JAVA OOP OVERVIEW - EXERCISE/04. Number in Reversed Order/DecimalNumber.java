package decimal_number;

import java.text.DecimalFormat;

public class DecimalNumber {

    public void printAllDigits(double number) {
        if (String.valueOf(number).contains(".")) {
        int num = Integer.parseInt(String.valueOf(number).split("\\.")[1]);
            if (num == 0) {
                int n = Integer.parseInt(String.valueOf(number).split("\\.")[0]);
                StringBuilder sb = new StringBuilder(n + "");
                System.out.println(sb.reverse());
                return;
            }
        }

        StringBuilder sb = new StringBuilder(number + "");
        System.out.println(sb.reverse());
    }
}
