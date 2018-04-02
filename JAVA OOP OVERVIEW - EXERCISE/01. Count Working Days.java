import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String firstDate = input.nextLine();
        String secondDate = input.nextLine();
        int startDay = Integer.parseInt(firstDate.split("-")[0]);
        int startMonth = Integer.parseInt(firstDate.split("-")[1]);
        int startYear = Integer.parseInt(firstDate.split("-")[2]);

        int endDay = Integer.parseInt(secondDate.split("-")[0]);
        int endMonth = Integer.parseInt(secondDate.split("-")[1]);
        int endYear = Integer.parseInt(secondDate.split("-")[2]);

        LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
        LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);

        int workingDays = 0;

        for (LocalDate date = startDate; date.isBefore(endDate.plusDays(1)); date = date.plusDays(1)) {
            int currentDay = Integer.parseInt(date.toString().split("-")[2]);
            int currentMonth = Integer.parseInt(date.toString().split("-")[1]);
            DayOfWeek day = date.getDayOfWeek();

            if (day.toString().equalsIgnoreCase("SATURDAY") ||
                    (day.toString().equalsIgnoreCase("SUNDAY")) ||
                    (currentDay == 1 && currentMonth == 1) ||
                    (currentDay == 3 && currentMonth == 3) ||
                    (currentDay == 1 && currentMonth == 5) ||
                    (currentDay == 6 && currentMonth == 5) ||
                    (currentDay == 24 && currentMonth == 5) ||
                    (currentDay == 6 && currentMonth == 9) ||
                    (currentDay == 22 && currentMonth == 9) ||
                    (currentDay == 1 && currentMonth == 11) ||
                    (currentDay == 24 && currentMonth == 12) ||
                    (currentDay == 25 && currentMonth == 12) ||
                    (currentDay == 26 && currentMonth == 12)) {
                continue;
            }

        workingDays++;
        }

        System.out.println(workingDays);
    }
}
