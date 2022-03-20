package todoApp;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todolist")
public class ToDoItem {
    private String name;
    private String description;
    private boolean status= false;
    private String userMail;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setZeitraum(long zeitraum) {
        this.zeitraum = zeitraum;
    }

    @Id
    private String id;

    public String getFormattedEndDate() {
        Instant now = Instant.now();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MM yyyy");
        return formatter.format(Date.from(now.plus(Duration.ofDays(zeitraum))));
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    private long zeitraum;

    public long getZeitraum() {
        return zeitraum;
    }

    public ToDoItem(String name, String description, long zeitraum, String userMail) {
        this.name = name;
        this.description = description;
        this.zeitraum = zeitraum;
        this.userMail =userMail;



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
