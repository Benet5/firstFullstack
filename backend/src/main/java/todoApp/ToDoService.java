package todoApp;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;



@Service
public class ToDoService {
    private final ToDoRepo todorepo;

    public ToDoService(ToDoRepo todorepo) {
        this.todorepo = todorepo;
    }



    public void addItem(ToDoItem newitem) {
        todorepo.save(newitem);
    }

    public List<ToDoItem> getAllItems() {
        return todorepo.findAll();
                //.stream().sorted(Comparator.comparing(ToDoItem::getFormattedEndDate)).toList();
    }

    public ToDoItem getItemByName(String name) {
        Optional <ToDoItem> result =todorepo.findByName(name);
        if (result.isPresent()) {
            return result.get();
        } else throw new RuntimeException("Das ToDo gibt es nicht!");
    }



    public ToDoItem getItemById(String id){
        Optional <ToDoItem> result = todorepo.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else throw new RuntimeException("Das ToDo gibt es nicht!");
    }


    public List <ToDoItem>deleteCheckedItems() {
        var todelete = todorepo.findAll().stream().filter(e -> !e.isStatus()).toList();
        todorepo.deleteAll(todelete);
        return todorepo.findAll();
    }

    public Optional<ToDoItem> deleteItem(String name) {
        return todorepo.deleteByName(name);
    }

    public List<ToDoItem> itemsByDeadline(String deadline) {
        return todorepo.findAllByFormattedEndDate(deadline);
    }

    public void updateItem(String id, ToDoItem item) {
        ToDoItem oldtodo= todorepo.findById(id).get();
        oldtodo.setName(item.getName());
        oldtodo.setDescription(item.getDescription());
        oldtodo.setZeitraum(item.getZeitraum());
    }

    public void checkItemId(String id) {
        var result = todorepo.findById(id);
        if (result.isPresent()) {
            if (!result.get().isStatus()) {
                result.get().setStatus(true);

            } else {
                result.get().setStatus(false);
            }
            todorepo.save(result.get());
        } else throw new RuntimeException("Das ToDo gibt es nicht!");
    }

    public void checkItem(String name) {
        var result = todorepo.findByName(name);
        if (result.isPresent()) {
            if (!result.get().isStatus()) {
                result.get().setStatus(true);
            } else {
                result.get().setStatus(false);
            }
            todorepo.save(result.get());
        } else throw new RuntimeException("Das ToDo gibt es nicht!");
    }
}