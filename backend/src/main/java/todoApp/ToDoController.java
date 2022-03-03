package todoApp;

import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/todoapp")
@CrossOrigin
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

    @PutMapping("/checkitemid/{id}")
    public void checkItemId(@PathVariable String id){
        service.checkItemId(id);
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

    @PutMapping("/checkeditems")
    public List<ToDoItem> deleteCheckedItems(){
        return service.deleteCheckedItems();
    }
}
