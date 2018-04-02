package average_grades;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {

    private String name;
    private double[] grades;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double[] getGrades() {
        return grades;
    }

    public void setGrades(double[] grades) {
        this.grades = grades;
    }

    public double averageGrade() {
        double sum = 0;
        for (double grade : this.grades) {
            sum += grade;
        }

        return sum / this.grades.length;
    }
}
