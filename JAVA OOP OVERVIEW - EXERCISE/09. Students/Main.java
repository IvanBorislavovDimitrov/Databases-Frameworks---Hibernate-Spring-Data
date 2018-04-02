package students;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while (! (line = input.readLine()).equals("End")) {
            Student student = new Student(line);
        }

        System.out.println(Student.countOfStudents);
    }
}
