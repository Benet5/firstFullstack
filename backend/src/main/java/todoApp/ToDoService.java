package todoApp;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;
import java.util.List;

@Service
public class ToDoService {
    private ToDoRepo todorepo;

    public ToDoService(ToDoRepo todorepo) {
        this.todorepo = todorepo;
    }

    public void addItem(ToDoItem newitem) {
        todorepo.addItem(newitem);
    }

    public List<ToDoItem> getAllItems() {
        return todorepo.getAllItems();
    }

    public ToDoItem getItemByName(String name) {
        return todorepo.getItemByName(name);
    }

    public ToDoItem getItemById(String id) {
        return todorepo.getItemById(id);
    }

    public List<ToDoItem> deleteCheckedItems() {
        return todorepo.deleteCheckedItems();
    }

    public void deleteItem(String name) {
        todorepo.deleteItem(name);
    }

    public void checkItemId(String id) {
        todorepo.checkItemId(id);
    }

    public void checkItem(String name) {
        todorepo.checkItem(name);
    }

    public List<ToDoItem> itemsByDeadline(String deadline) {
        return todorepo.itemsByDeadline(deadline);
    }


    public void updateItem(String id, ToDoItem item) {
        todorepo.updateItem(id, item);
    }
}