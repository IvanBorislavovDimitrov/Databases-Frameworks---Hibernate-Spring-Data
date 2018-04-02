package average_grades;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int countOfStudents = Integer.parseInt(input.readLine());
        Student[] students = new Student[countOfStudents];

        for (int i = 0; i < countOfStudents; i++) {
            String[] infoAboutStudent = input.readLine().split("\\s+");
            String studentName = infoAboutStudent[0];
            students[i] = new Student(studentName);

            students[i].setGrades(Arrays.stream(infoAboutStudent).skip(1).mapToDouble(Double::parseDouble).toArray());
        }

        Arrays.sort(students, (x1, x2) -> {
            if (x1.getName().compareTo(x2.getName()) == 0) {
                return Double.compare(x2.averageGrade(), x1.averageGrade());
            }

            return x1.getName().compareTo(x2.getName());
        });

        for (Student student : students) {
            double avrGrade = student.averageGrade();
            if (avrGrade >= 5) {
                System.out.println(String.format("%s -> %.2f", student.getName(), student.averageGrade()));
            }
        }
    }
}
