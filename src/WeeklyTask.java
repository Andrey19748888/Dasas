import java.time.LocalDateTime;

public class WeeklyTask extends Task {

    public WeeklyTask(String name, String description, Type type, LocalDateTime dateTime) {
        super(name, description, type, dateTime);
    }

    @Override
    public LocalDateTime nextDateTime() {
        return getDateTime().plusWeeks(1);
    }
}
