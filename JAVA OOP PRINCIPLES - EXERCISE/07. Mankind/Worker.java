package mankind;

public class Worker extends Human {

    private double weekSalary;
    private double workingHours;

    public Worker(String firstName, String lastName, double weekSalary, double workingHours) {
        super(firstName, lastName);
        this.setWeekSalary(weekSalary);
        this.setWorkingHours(workingHours);
    }

    public double getWeekSalary() {
        return weekSalary;
    }

    public void setWeekSalary(double weekSalary) {
        if (weekSalary < 10) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: weekSalary");
        }
        this.weekSalary = weekSalary;
    }

    public double getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(double workingHours) {
        if (workingHours < 1 || workingHours > 12) {
            throw new IllegalArgumentException("Expected value mismatch!Argument: workHoursPerDay");
        }
        this.workingHours = workingHours;
    }

    @Override
    public void setLastName(String lastName) {
        if (! Character.isUpperCase(lastName.charAt(0))) {
            throw new IllegalArgumentException("Expected upper case letter!Argument: lastName");
        } else if (lastName.length() < 3) {
            throw new IllegalArgumentException("Expected length at least 3 symbols!Argument: lastName");
        }
        this.lastName = lastName;
    }

    private double calculateSalaryPerHour() {
        return this.getWeekSalary() / 7 / this.getWorkingHours();
    }

    @Override
    public String toString() {
        return String.format("First Name: %s%n" +
                "Last Name: %s%n" +
                "Week Salary: %.2f%n" +
                "Hours per day: %.2f%n" +
                "Salary per hour: %.2f%n", this.getFirstName(), this.getLastName(), this.getWeekSalary(), this.getWorkingHours(),
                        this.calculateSalaryPerHour());
    }
}
