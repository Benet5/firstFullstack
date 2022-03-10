package todoApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ToDoServiceTest {
    @Test
    void Shouldremovechecked(){
        List<ToDoItem> todo = new ArrayList<>();
        ToDoRepo toDoRepo = new ToDoRepo(todo);
        ToDoService service= new ToDoService(toDoRepo);

        ToDoItem newitem = new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen",0);
        ToDoItem newitem1 = new ToDoItem("Milch", "Für den gesamten Kurs 15 Tassen",0);
        toDoRepo.addItem(newitem);
        toDoRepo.addItem(newitem1);


        service.checkItem("Milch");

        toDoRepo.deleteCheckedItems();
        assertThat(todo.size() ==1);
        assertThat(toDoRepo.getAllItems().contains(newitem));

    }

    @Test

    void ShouldSetStatusTrue(){
        List<ToDoItem> todo = new ArrayList<>();
        ToDoRepo toDoRepo = new ToDoRepo(todo);
        ToDoService service= new ToDoService(toDoRepo);

        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen",0);
        ToDoItem newitem2 = new ToDoItem("Kaffekochen", "Für den gesamten Kurs 15 Tassen",0);
        toDoRepo.addItem(newitem1);
        toDoRepo.addItem(newitem2);

        service.checkItem("Milch");
        service.checkItem("Kaffekochen");
        service.checkItem("Kaffekochen");
        Assertions.assertTrue(newitem1.isStatus());
        Assertions.assertFalse(newitem2.isStatus());

    }




}