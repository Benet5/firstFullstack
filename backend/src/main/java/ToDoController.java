import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
@RestController
@RequestMapping("/todoapp")
public class ToDoController {

    private ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    @PostMapping("/addtodo")
    public  void addItem(@RequestBody ToDoItem newitem){
        service.addItem(newitem);
    }

    @GetMapping("/getitembyname/{additem}")
    public ToDoItem getItemByName(@PathVariable String name){
        return service.getItemByName(name);
    }




    public void deleteItem( String name){
        service.deleteItem(name);
    }

    public void checkItem(String name){
        service.checkItem(name);
    }




}
