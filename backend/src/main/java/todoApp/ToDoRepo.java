package todoApp;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Repository
public class ToDoRepo {
    List<ToDoItem> todo;

    public ToDoRepo(List<ToDoItem> todo) {
        this.todo = todo;
    }

    public void addItem(ToDoItem newitem){
        todo.add(newitem);
    }

    public List <ToDoItem> getAllItems(){
        return todo;
    }

    public ToDoItem getItemByName(String name){
        var result = searchitem(name);
        if (result.isPresent()) {
            return result.get();
        } else throw new RuntimeException("Das ToDo gibt es nicht!");
    }


    public void deleteItem(String name){
        var result = searchitem(name);
        if (result.isPresent()) {
            todo.remove(result.get());
        } else throw new RuntimeException("Das ToDo gibt es nicht!");

    }


    public void checkItem(String name){
        var result = searchitem(name);
        if (result.isPresent()) {
             if(!result.get().isStatus()){
                 result.get().setStatus(true);
             }else {result.get().setStatus(false);
        } }
             else throw new RuntimeException("Das ToDo gibt es nicht!");
    }
// Frontend-Bedingung: Namen der To-Dos müssen einzigartig sein, ergo weiteren Service für Gegencheck bereitstellen.
    public Optional<ToDoItem> searchitem(String name){
        var todostream =todo.stream();
        var result = todostream.filter(e -> e.getName().toLowerCase(Locale.ROOT).equals(name.toLowerCase(Locale.ROOT)))
                .findFirst();
        return result;
    }

    //remove all Funktion

}
