import java.time.LocalDateTime;

public class MonthlyTask extends Task{


    public MonthlyTask(String name, String description, Type type, LocalDateTime dateTime) {
        super(name, description, type, dateTime);
    }

    @Override
    public LocalDateTime nextDateTime() {
        return getDateTime().plusMonths(1);
    }
}
