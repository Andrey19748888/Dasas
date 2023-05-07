import java.time.LocalDateTime;

public class YearlyTask extends Task {

    public YearlyTask(String name, String description, Type type, LocalDateTime dateTime) {
        super(name, description, type, dateTime);
    }

    @Override
    public LocalDateTime nextDateTime() {
        return getDateTime().plusYears(1);
    }
}
