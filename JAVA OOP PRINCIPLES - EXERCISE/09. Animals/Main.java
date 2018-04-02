package animals;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (! (line = input.readLine()).equals("Beast!")) {
            String[] lineArgs = input.readLine().split("\\s+");
            try {
                Animal animal = summonAnimal(line);
                if (animal == null) {
                    throw new IllegalArgumentException("Invalid input!");
                }
                animal.setName(lineArgs[0]);
                animal.setAge(Integer.parseInt(lineArgs[1]));
                animal.setGender(lineArgs[2]);
                System.out.println(line);
                System.out.println(animal);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Animal summonAnimal(String line) {
        switch (line) {
            case "Cat":
                return new Cat();
            case "Dog":
                return new Dog();
            case "Frog":
                return new Frog();
            case "Kitten":
                return new Kitten();
            case "Tomcat":
                return new Tomcat();
        }

        return null;
    }
}
