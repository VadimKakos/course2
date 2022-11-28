package course2;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Task implements Repeatable {


    private int id;
    private String title;
    private String description;

    private Type type;
    private LocalDateTime dateTime;
    private static int idGen = 0;

    public Task(String title, String description, Type type, LocalDateTime dateTime) {
        this.id = idGen++;
        this.title = title;
        this.description = description;
        this.type = type;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static int getIdGen() {
        return idGen;
    }

    public static void setIdGen(int idGen) {
        Task.idGen = idGen;
    }

    @Override

    public boolean isAvailable(LocalDate dateTime) {
        return dateTime.isEqual(getDateTime().toLocalDate());
    }

    @Override
    public String toString() {
        return "Задача {" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", dateTime=" + dateTime +
                '}';
    }
}
