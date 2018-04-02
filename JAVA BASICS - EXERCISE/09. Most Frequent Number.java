import java.util.*;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] elements = input.nextLine().split("\\s+");
        int[] sequenceOfElements = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            sequenceOfElements[i] = Integer.parseInt(elements[i]);
        }

        int number = 0;
        int bestCount = 1;
        for (int i = 0; i < sequenceOfElements.length; i++) {
            int currentCount = 1;

            for (int j = 0; j < sequenceOfElements.length; j++) {
                if (sequenceOfElements[i] == sequenceOfElements[j]) {
                    currentCount++;
                    if (currentCount > bestCount) {
                        bestCount = currentCount;
                        number = sequenceOfElements[i];
                    }
                }
            }
        }

        System.out.println(number);
    }
}
