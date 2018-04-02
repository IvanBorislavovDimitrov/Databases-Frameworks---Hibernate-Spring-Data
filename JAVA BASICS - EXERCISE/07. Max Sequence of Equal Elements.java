import java.util.*;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] elements = input.nextLine().split("\\s+");
        int[] sequenceOfElements = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            sequenceOfElements[i] = Integer.parseInt(elements[i]);
        }
        int startPos = 0;
        int bestStart = 0;
        int endPos = 0;
        int bestLength = 1;
        int currentLength = 1;
        for (int i = 1; i < sequenceOfElements.length; i++) {
            if (sequenceOfElements[i] == sequenceOfElements[i - 1]) {
                currentLength++;
                if (currentLength > bestLength) {
                    bestLength = currentLength;
                    endPos = i;
                    bestStart = startPos;
                }
            } else {
                startPos = i;
                currentLength = 1;
            }
        }

        for (int i = bestStart; i <= endPos; i++) {
            System.out.printf("%d ", sequenceOfElements[i]);
        }
        System.out.println();
    }
}
