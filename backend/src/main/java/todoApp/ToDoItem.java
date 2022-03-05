package todoApp;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public class ToDoItem {
    private String name;
    private String description;
    private boolean status= false;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setFormattedEndDate(String formattedEndDate) {
        this.formattedEndDate = formattedEndDate;
    }

    private String id;

    public String getFormattedEndDate() {
        return formattedEndDate;
    }
    private String formattedEndDate;



    public ToDoItem(String name, String description, long zeitraum) {
        this.name = name;
        this.description = description;
        Instant now = Instant.now();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        this.formattedEndDate = formatter.format(Date.from(now.plus(Duration.ofDays(zeitraum))));
        this.id = UUID.randomUUID().toString();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoItem toDoItem = (ToDoItem) o;
        return isStatus() == toDoItem.isStatus() && Objects.equals(getName(), toDoItem.getName()) && Objects.equals(getDescription(), toDoItem.getDescription());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String getId(){
        return id;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
