import java.util.*;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String[] elements = input.nextLine().split("\\s+");
        int[] sequenceOfElements = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            sequenceOfElements[i] = Integer.parseInt(elements[i]);
        }

        boolean isThereSuchIndex = false;
        for (int i = 0; i < sequenceOfElements.length; i++) {
            int sum1 = 0;
            int sum2 = 0;

            for (int left = i - 1; left >= 0; left--) {
                sum1 += sequenceOfElements[left];
            }

            for (int right = i + 1; right < sequenceOfElements.length; right++) {
                sum2 += sequenceOfElements[right];
            }

            if (sum1 == sum2) {
                System.out.println(i);
                isThereSuchIndex = true;
                break;
            }
        }

        if (! isThereSuchIndex) {
            System.out.println("no");
        }
    }
}
