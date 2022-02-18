package todoApp;

import java.util.Objects;

public class ToDoItem {
    private String name;
    private String description;
    //private SimpleDateFormat enddatum;
    private boolean status= false;

    /* Datum mit instant und Date erabeiten, s.a. Screenshot von THomas

     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToDoItem toDoItem = (ToDoItem) o;
        return isStatus() == toDoItem.isStatus() && Objects.equals(getName(), toDoItem.getName()) && Objects.equals(getDescription(), toDoItem.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), isStatus());
    }

    public ToDoItem(String name, String description) {
        this.name = name;
        this.description = description;

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

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
