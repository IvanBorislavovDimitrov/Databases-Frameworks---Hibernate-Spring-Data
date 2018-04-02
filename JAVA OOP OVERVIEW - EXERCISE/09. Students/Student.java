package students;

public class Student {

    public static int countOfStudents = 0;
    private String name;

    public Student(String name) {
        this.name = name;
        countOfStudents++;
    }

    public static int getCountOfStudents() {
        return countOfStudents;
    }

    public static void setCountOfStudents(int countOfStudents) {
        Student.countOfStudents = countOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
