package course2;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MonthTask extends Task{
    public MonthTask(String title, String description, Type type, LocalDateTime dateTime) {
        super(title, description, type, dateTime);
    }

    @Override
    public boolean isAvailable(LocalDate dateTime) {
        var startDate = getDateTime().toLocalDate();
        while (!startDate.isAfter(dateTime)) {
            if (startDate.equals(dateTime)) {
                return true;
            }
            startDate = startDate.plusMonths(1);
        }
        return false;
    }
}
