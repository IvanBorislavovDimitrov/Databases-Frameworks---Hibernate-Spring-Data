import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;

public class FindAndSumIntegers {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

        String line = input.readLine();

        String[] allWords = line.split("\\s+");

        boolean isThereNumber = false;
        for (String word : allWords) {
            if (isNumber(word)) {
                isThereNumber = true;
                break;
            }
        }

        if (! isThereNumber) {
            System.out.println("No match");
            return;
        }

        double sum = Arrays.stream(allWords).filter(FindAndSumIntegers::isNumber).mapToDouble(Double::parseDouble).sum();

        System.out.println(new DecimalFormat().format(sum));
    }

    private static boolean isNumber(String num) {
        try {
            double a = Double.parseDouble(num);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
