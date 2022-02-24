package todoApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ToDoRepoTest {


    @Test
    void ShouldAddAnItem(){
        List<ToDoItem> todo = new ArrayList<>();
        ToDoRepo toDoRepo = new ToDoRepo(todo);

        ToDoItem newitem = new ToDoItem("Kaffekochen", "Für den gesamten Kurs 15 Tassen",0);
        toDoRepo.addItem(newitem);

        Assertions.assertEquals(todo.get(0), newitem);

    }

    @Test
    void ShouldgetAllItems(){
        List<ToDoItem> todo = new ArrayList<>();
        ToDoRepo toDoRepo = new ToDoRepo(todo);
        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen",0);
        ToDoItem newitem2 = new ToDoItem("Kaffekochen", "Für den gesamten Kurs 15 Tassen",0);
        toDoRepo.addItem(newitem1);
        toDoRepo.addItem(newitem2);

        var actual = toDoRepo.getAllItems();

        assertEquals(todo, actual);

    }

    @Test
    void ShouldGetAnItemNotexistant(){
        List<ToDoItem> todo = new ArrayList<>();
        ToDoRepo toDoRepo = new ToDoRepo(todo);

        ToDoItem newitem = new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen",0);
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

        ToDoItem newitem = new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen",0);
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
        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen",0);
        ToDoItem newitem2 = new ToDoItem("Kaffekochen", "Für den gesamten Kurs 15 Tassen",0);
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
        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen",0);
        ToDoItem newitem2 = new ToDoItem("Kaffekochen", "Für den gesamten Kurs 15 Tassen",0);
        toDoRepo.addItem(newitem1);
        toDoRepo.addItem(newitem2);

        toDoRepo.checkItem("Milch");
        toDoRepo.checkItem("Kaffekochen");
        toDoRepo.checkItem("Kaffekochen");
        Assertions.assertTrue(newitem1.isStatus());
        Assertions.assertFalse(newitem2.isStatus());

    }

    @Test
    void ShouldGetAllItemsbyDate(){
        ToDoItem newitem1 = new ToDoItem("Kaffee", "ganze Bohnen!", 2);
        ToDoItem newitem2 = new todoApp.ToDoItem("Tanken", "maximal bis 50€",2);
        ToDoItem newitem3 = new ToDoItem("KiTa-Platz", "klären bzgl frühestmöglicher Anmeldung",3);
        ToDoItem newitem4 = new todoApp.ToDoItem("Sturmschaden", "mit der Hausverwaltung schnacken",3);


        List <ToDoItem> todo = List.of(newitem1, newitem2 ,newitem3);
        ToDoRepo toDoRepo = new ToDoRepo(todo);


        var actual = toDoRepo.itemsByDeadline("26 02 2022"); //hier immer vom heutigen Tag ausgehen
        assertEquals(2, actual.size());

        var actual1 = toDoRepo.itemsByDeadline("27 02 2022");
        assertEquals(1, actual1.size());
        assertEquals(newitem3, actual1.get(0));

    }

@Test
void ShouldgetItembyID(){
    List<ToDoItem> todo = new ArrayList<>();
    ToDoRepo toDoRepo = new ToDoRepo(todo);

    ToDoItem newitem = new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen",0);
    toDoRepo.addItem(newitem);

    var actual = toDoRepo.getItemById(newitem.getId());
    Assertions.assertEquals(newitem, actual);

    try {
        var actual2 = toDoRepo.getItemById("Milchs");
    }catch
    (RuntimeException e){
        System.out.println("Das ToDo gibt es nicht!");
    }

}
}




        /*
        SimpleDateFormat enddatum = new SimpleDateFormat();
     enddatum.getTime().;
Instant new = Instant.now();
Date iat = Date.from(now);
Date exp =Date.from(now).plus(Duration.of Hour(eingabeIT)

   */