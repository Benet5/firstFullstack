package todoApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ToDoRepoTest {


    @Test
    void ShouldAddAnItem(){
        List<ToDoItem> todo = new ArrayList<>();
        ToDoRepo toDoRepo = new ToDoRepo(todo);

        ToDoItem newitem = new ToDoItem("Kaffekochen", "Für den gesamten Kurs 15 Tassen");
        toDoRepo.addItem(newitem);

        Assertions.assertEquals(todo.get(0), newitem);

    }

    @Test
    void ShouldgetAllItems(){
        List<ToDoItem> todo = new ArrayList<>();
        ToDoRepo toDoRepo = new ToDoRepo(todo);
        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen");
        ToDoItem newitem2 = new ToDoItem("Kaffekochen", "Für den gesamten Kurs 15 Tassen");
        toDoRepo.addItem(newitem1);
        toDoRepo.addItem(newitem2);

        var actual = toDoRepo.getAllItems();

        assertEquals(todo, actual);

    }

    @Test
    void ShouldGetAnItemNotexistant(){
        List<ToDoItem> todo = new ArrayList<>();
        ToDoRepo toDoRepo = new ToDoRepo(todo);

        ToDoItem newitem = new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen");
        try {
            var actual = toDoRepo.getItemByName("Kaffeekochen");
        }catch
            (RuntimeException e){
            System.out.println("Das ToDo gibt es nicht!");
            }

    }



    @Test
    void ShouldGetAnItem(){
        List<ToDoItem> todo = new ArrayList<>();
    ToDoRepo toDoRepo = new ToDoRepo(todo);

        ToDoItem newitem = new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen");
        todo.add(newitem);

        var actual = toDoRepo.getItemByName("Kaffeekochen");
        Assertions.assertEquals(newitem, actual);
        var actual1 = toDoRepo.getItemByName("KaffEekochen");
        Assertions.assertEquals(newitem, actual1);

        try {
            var actual2 = toDoRepo.getItemByName("Milchs");
        }catch
        (RuntimeException e){
            System.out.println("Das ToDo gibt es nicht!");
        }

    }

    @Test
    void ShouldDeleteanItem(){
        List<ToDoItem> todo = new ArrayList<>();
        ToDoRepo toDoRepo = new ToDoRepo(todo);
        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen");
        ToDoItem newitem2 = new ToDoItem("Kaffekochen", "Für den gesamten Kurs 15 Tassen");
        toDoRepo.addItem(newitem1);
        toDoRepo.addItem(newitem2);

       toDoRepo.deleteItem("Milch");


        assertTrue(todo.size() == 1);
        try {
            var actual = toDoRepo.getItemByName("Milch");
        }catch
        (RuntimeException e){
            System.out.println("Das ToDo gibt es nicht!");
        }

    }

    @Test

    void ShouldSetStatusTrue(){
        List<ToDoItem> todo = new ArrayList<>();
        ToDoRepo toDoRepo = new ToDoRepo(todo);
        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen");
        ToDoItem newitem2 = new ToDoItem("Kaffekochen", "Für den gesamten Kurs 15 Tassen");
        toDoRepo.addItem(newitem1);
        toDoRepo.addItem(newitem2);

        toDoRepo.checkItem("Milch");
        toDoRepo.checkItem("Kaffekochen");
        toDoRepo.checkItem("Kaffekochen");
        Assertions.assertTrue(newitem1.isStatus());
        Assertions.assertFalse(newitem2.isStatus());

    }




}


        /*
        SimpleDateFormat enddatum = new SimpleDateFormat();
     enddatum.getTime().;
Instant new = Instant.now();
Date iat = Date.from(now);
Date exp =Date.from(now).plus(Duration.of Hour(eingabeIT)

   */