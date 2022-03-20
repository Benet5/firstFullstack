package todoApp;

import org.springframework.web.bind.annotation.*;


import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/todoapp")
@CrossOrigin( origins ={"http://localhost:3000"})
public class ToDoController {

    private ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    @PostMapping
    public ToDoItem addItem(@RequestBody ToDoItem newitem, Principal principal){
        return service.addItem(newitem, principal);
    }

    @GetMapping("/getitembyname/{name}")
    public ToDoItem getItemByName(@PathVariable String name, Principal principal){
        return service.getItemByName(name, principal);
    }

    @GetMapping("/getitembyid/{id}")
    public ToDoItem getItemById(@PathVariable String id){
        return service.getItemById(id);
    }

    @DeleteMapping ("/deleteitem/{name}")
    public void deleteItem(@PathVariable String name, Principal principal){
        service.deleteItem(name, principal);
    }

    @PutMapping("/checkitemid/{id}")
    public void checkItemId(@PathVariable String id){
        service.checkItemId(id);
    }

    @PutMapping("/checkitem/{name}")
    public void checkItem(@PathVariable String name){
        service.checkItem(name);
    }

    @GetMapping ("/getallitems")
    public List<ToDoItem> getAllItems(Principal principal){
        return service.getAllItems(principal);
    }

    @GetMapping("/getbydate/{deadline}")
    public List<ToDoItem> getItemsByDate(@PathVariable String deadline, Principal principal){
        return service.itemsByDeadline(deadline, principal);
    }

    @PutMapping("/checkeditems")
    public List<ToDoItem> deleteCheckedItems(Principal principal){
        return service.deleteCheckedItems(principal);
    }

    @PutMapping("/updateitem/{id}")
    public void updateItem(@PathVariable String id, @RequestBody ToDoItem item, Principal principal) {
        service.updateItem(id, item, principal);
    }
}
