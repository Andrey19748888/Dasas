import java.time.LocalDateTime;

public class DailyTask extends Task {

    public DailyTask(String name, String description, Type type, LocalDateTime dateTime) {
        super(name, description, type, dateTime);
    }

    @Override
    public LocalDateTime nextDateTime() {
        return getDateTime().plusDays(1);
    }

}
