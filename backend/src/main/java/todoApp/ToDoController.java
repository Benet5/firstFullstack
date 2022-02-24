package todoApp;

import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/todoapp")
public class ToDoController {

    private ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    @PostMapping
    public void addItem(@RequestBody ToDoItem newitem){
        service.addItem(newitem);
    }

    @GetMapping("/getitembyname/{name}")
    public ToDoItem getItemByName(@PathVariable String name){
        return service.getItemByName(name);
    }

    @GetMapping("/getitembyid/{id}")
    public ToDoItem getItemById(@PathVariable String id){
        return service.getItemById(id);
    }

    @DeleteMapping ("/deleteitem/{name}")
    public void deleteItem(@PathVariable String name){
        service.deleteItem(name);
    }

    @PutMapping("/checkitem/{name}")
    public void checkItem(@PathVariable String name){
        service.checkItem(name);
    }

    @GetMapping ("/getallitems")
    public List<ToDoItem> getAllItems(){
        return service.getAllItems();
    }

    @GetMapping("/getbydate/{deadline}")
    public List<ToDoItem> getItemsByDate(@PathVariable String deadline){
        return service.itemsByDeadline(deadline);
    }


}
