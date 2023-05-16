import java.time.LocalDate;
import java.time.LocalDateTime;

public abstract class Task implements Frequency {


    private static int counter = 5;

    private String name;
    private String description;
    private Type type;
    private LocalDateTime dateTime;
    private int id;
    private boolean isDeleted;


    public Task(String name, String description, Type type, LocalDateTime dateTime) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.dateTime = dateTime;

        id = counter;
        counter++;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    @Override
    public abstract LocalDateTime nextDateTime();

    @Override
    public String toString() {
        return "Задача " + id +
                ": name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", dateTime=" + dateTime;
    }

    public static int getCounter() {
        return counter;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
