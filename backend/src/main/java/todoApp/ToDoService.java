package todoApp;

import org.springframework.stereotype.Service;

import java.security.Principal;
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



    public ToDoItem addItem(ToDoItem newitem, Principal principal) {
         newitem.setUserMail(principal.getName());
        return todorepo.save(newitem);
    }

    public List<ToDoItem> getAllItems(Principal principal) {
        return todorepo.findAllByUserMail(principal.getName());
                //.stream().sorted(Comparator.comparing(ToDoItem::getFormattedEndDate)).toList();
    }

    public ToDoItem getItemByName(String name, Principal principal) {
        Optional <ToDoItem> result =todorepo.findByName(name);
        if (result.isPresent() && result.get().getUserMail().equals(principal.getName())) {
            return result.get();
        } else throw new RuntimeException("Das ToDo gibt es nicht!");
    }



    public ToDoItem getItemById(String id){
        Optional <ToDoItem> result = todorepo.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else throw new RuntimeException("Das ToDo gibt es nicht!");
    }
/*
    public List <ToDoItem>deleteCheckedItems(Principal principal) {
       List<ToDoItem> toDelete = todorepo.findAllByUserMail(principal.getName());
        List<ToDoItem> toDelete2 =toDelete.stream().filter(e -> e.isStatus()).toList();
        todorepo.deleteAll(toDelete2);
       return todorepo.findAll();
    }
    */

  public List <ToDoItem> deleteCheckedItems(Principal principal){
      todorepo.deleteAllByUserMailAndStatusTrue(principal.getName());
      return todorepo.findAllByUserMail(principal.getName());
  }


   /*
   public List <ToDoItem>deleteCheckedItems() {
        var todelete = todorepo.findAll().stream().filter(e -> e.isStatus()).toList();
        todorepo.deleteAll(todelete);
        return todorepo.findAll();
    }
    */

    public Optional<ToDoItem> deleteItem(String name, Principal principal) {
        if (todorepo.findByName(name).isPresent() && (todorepo.findByName(name).get().getUserMail().equals(principal.getName()))) {
            return todorepo.deleteByName(name);
            } else throw new RuntimeException("Das hat nicht geklappt.");
    }

    public List<ToDoItem> itemsByDeadline(String deadline, Principal principal) {
        return todorepo.findAllByFormattedEndDateAndUserMail(deadline, principal.getName());
    }

    public void updateItem(String id, ToDoItem item, Principal principal) {
        Optional <ToDoItem> oldtodo= todorepo.findById(id);
        if(oldtodo.isPresent() && oldtodo.get().getUserMail().equals(principal.getName())){
            oldtodo.get().setName(item.getName());
            oldtodo.get().setDescription(item.getDescription());
            oldtodo.get().setZeitraum(item.getZeitraum());
            todorepo.save(oldtodo.get());
        }
        else throw new RuntimeException("Das Todo gibt es nicht oder du bist nicht berechtigt");
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