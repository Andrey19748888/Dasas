import java.time.LocalDateTime;

public class SingleTask extends Task
{
    public SingleTask(String name, String description, Type type, LocalDateTime dateTime) {
        super(name, description, type, dateTime);
    }

    @Override
    public LocalDateTime nextDateTime() {
        System.out.println("Эта задача одноразовая, следующей даты нет, nextDateTime возвращает null");
        return null;
    }
}
