package todoApp;

import org.springframework.web.bind.annotation.*;

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


}
