package todoApp;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService {
    private ToDoRepo todorepo;

    public ToDoService(ToDoRepo todorepo) {
        this.todorepo = todorepo;
    }

    public  void addItem(ToDoItem newitem){
        todorepo.addItem(newitem);
    }

    public List<ToDoItem> getAllItems(){
        return todorepo.getAllItems();
    }

    public ToDoItem getItemByName(String name){
        return todorepo.getItemByName(name);
    }

    public void deleteItem( String name){
        todorepo.deleteItem(name);
    }

    public void checkItem(String name){
        todorepo.checkItem(name);
    }
}
