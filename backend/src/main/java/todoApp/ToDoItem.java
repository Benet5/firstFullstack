package todoApp;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class ToDoItem {
    private String name;
    private String description;
    private boolean status= false;

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


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
