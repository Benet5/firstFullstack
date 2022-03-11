package todoApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class ToDoServiceTest {




    @Test
    void Shouldremovechecked(){
        ToDoRepo todoRepo = mock(ToDoRepo.class);

        ToDoService service= new ToDoService(todoRepo);

        ToDoItem newitem = new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen",0);
        ToDoItem newitem1 = new ToDoItem("Milch", "Für den gesamten Kurs 15 Tassen",0);


        Mockito.when(todoRepo.save(newitem)).thenReturn((new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen",0)));
        Mockito.when(todoRepo.save(newitem1)).thenReturn((new ToDoItem("Milch", "Für den gesamten Kurs 15 Tassen",0)));
        service.addItem(newitem);
        service.addItem(newitem1);
        List<ToDoItem> todos = List.of(newitem);

        Mockito.when(todoRepo.findByName("Milch")).thenReturn(Optional.of(newitem1));
        service.checkItem("Milch");

        assertThat(newitem1.isStatus());


        Mockito.when(todoRepo.findAll()).thenReturn(todos);
        service.deleteCheckedItems();

        assertThat(todoRepo.findAll().contains(newitem));

    }

    @Test

    void ShouldSetStatusTrue(){

        ToDoRepo todoRepo = mock(ToDoRepo.class);
        ToDoService service= new ToDoService(todoRepo);

        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen",0);
        ToDoItem newitem2 = new ToDoItem("Kaffekochen", "Für den gesamten Kurs 15 Tassen",0);
        Mockito.when(todoRepo.save(newitem2)).thenReturn((new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen",0)));
        Mockito.when(todoRepo.save(newitem1)).thenReturn((new ToDoItem("Milch", "Für den gesamten Kurs 15 Tassen",0)));
        service.addItem(newitem1);
        service.addItem(newitem2);

        Mockito.when(todoRepo.findByName("Milch")).thenReturn(Optional.of(newitem1));
        Mockito.when(todoRepo.findByName("Kaffekochen")).thenReturn(Optional.of(newitem2));



        service.checkItem("Milch");
        service.checkItem("Kaffekochen");
        service.checkItem("Kaffekochen");
        Assertions.assertTrue(newitem1.isStatus());
        Assertions.assertFalse(newitem2.isStatus());

    }

    @Test
    void ShouldAddAndGetAnItem(){
        ToDoRepo todoRepo = mock(ToDoRepo.class);
        ToDoService service= new ToDoService(todoRepo);

        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen",0);
        ToDoItem newitem2 = new ToDoItem("Kaffekochen", "Für den gesamten Kurs 15 Tassen",0);

        List<ToDoItem> todos =List.of(newitem1, newitem2);
        Mockito.when(todoRepo.save(newitem2)).thenReturn((new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen",0)));
        Mockito.when(todoRepo.save(newitem1)).thenReturn((new ToDoItem("Milch", "Für den gesamten Kurs 15 Tassen",0)));
        service.addItem(newitem1);
        service.addItem(newitem2);

        Mockito.when(todoRepo.findAll()).thenReturn(todos);
        var result = service.getAllItems();

        Assertions.assertEquals(todos, result);

    }



    @Test
    void ShouldGetAnItemNotexistant(){
        ToDoRepo todoRepo = mock(ToDoRepo.class);
        ToDoService service= new ToDoService(todoRepo);

        ToDoItem newitem = new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen",0);

        try {
            var actual = service.getItemByName("Kaffekochen");
        }catch
        (RuntimeException e){
            System.out.println("Das ToDo gibt es nicht!");
        }

    }





    @Test
    void ShouldDeleteanItem(){
        ToDoRepo todoRepo = mock(ToDoRepo.class);
        ToDoService service= new ToDoService(todoRepo);

        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen",0);
        ToDoItem newitem2 = new ToDoItem("Kaffekochen", "Für den gesamten Kurs 15 Tassen",0);


        Mockito.when(todoRepo.save(newitem2)).thenReturn((new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen",0)));
        Mockito.when(todoRepo.save(newitem1)).thenReturn((new ToDoItem("Milch", "Für den gesamten Kurs 15 Tassen",0)));
        service.addItem(newitem1);
        service.addItem(newitem2);

        Mockito.when(todoRepo.deleteByName("Milch")).thenReturn(Optional.of(newitem1));
        var result= service.deleteItem("Milch");

        assertEquals(newitem1, result.get());

    }


        @Test
        void ShouldGetAllItemsbyDate(){
            ToDoItem newitem1 = new ToDoItem("Kaffee", "ganze Bohnen!", 2);
            ToDoItem newitem2 = new todoApp.ToDoItem("Tanken", "maximal bis 50€",2);
            ToDoItem newitem3 = new ToDoItem("KiTa-Platz", "klären bzgl frühestmöglicher Anmeldung",3);
            ToDoItem newitem4 = new todoApp.ToDoItem("Sturmschaden", "mit der Hausverwaltung schnacken",3);

            List <ToDoItem> list1 = List.of(newitem1, newitem2);
            List <ToDoItem> list2 = List.of(newitem3, newitem4);

            ToDoRepo todoRepo = mock(ToDoRepo.class);
            ToDoService service= new ToDoService(todoRepo);

            Mockito.when(todoRepo.findAllByFormattedEndDate("13 03 2022")).thenReturn(list1);
            var actual = service.itemsByDeadline("13 03 2022"); //hier immer vom heutigen Tag ausgehen
            assertEquals(2, actual.size());

            Mockito.when(todoRepo.findAllByFormattedEndDate("14 03 2022")).thenReturn(list2);
            var actual1 = service.itemsByDeadline("14 03 2022");
            assertEquals(2, actual1.size());
            assertEquals(newitem3, actual1.get(0));

        }
/*
    @Test
    void ShouldgetItembyID(){
        List<ToDoItem> todo = new ArrayList<>();
        altesRepo altesRepo = new altesRepo(todo);

        ToDoItem newitem = new ToDoItem("Kaffeekochen", "Für den gesamten Kurs 15 Tassen",0);
        altesRepo.addItem(newitem);

        var actual = altesRepo.getItemById(newitem.getId());
        Assertions.assertEquals(newitem, actual);

        try {
            var actual2 = altesRepo.getItemById("Milchs");
        }catch
        (RuntimeException e){
            System.out.println("Das ToDo gibt es nicht!");
        }

    }

*/



}