package todoApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDoControllerTestMock {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    ToDoService toDoService;

    @Test
    void ShouldGetAllItems(){
        ToDoItem newitem1 = new ToDoItem("Kaffee", "ganze Bohnen!",0 );
        ToDoItem newitem2 = new todoApp.ToDoItem("Tanken", "maximal bis 50€",0 );
        ToDoItem newitem3 = new ToDoItem("KiTa-Platz", "klären bzgl frühestmöglicher Anmeldung",0);
        ToDoItem newitem4 = new todoApp.ToDoItem("Sturmschaden", "mit der Hausverwaltung schnacken",0);

        List <ToDoItem> todos = List.of(newitem1, newitem2,newitem3, newitem4);

        when(toDoService.getAllItems()).thenReturn(todos);
        ResponseEntity<ToDoItem[]> response = restTemplate.getForEntity("/todoapp/getallitems", ToDoItem[].class);
        assertTrue((response.getBody()).length == 4);
        assertTrue(Arrays.stream(response.getBody()).toList().equals(todos));
    }


    @Test
    void ShouldGetAllItemsbyDate(){
        ToDoItem newitem1 = new ToDoItem("Kaffee", "ganze Bohnen!", 2);
        ToDoItem newitem2 = new todoApp.ToDoItem("Tanken", "maximal bis 50€",2);
        ToDoItem newitem3 = new ToDoItem("KiTa-Platz", "klären bzgl frühestmöglicher Anmeldung",3);
        ToDoItem newitem4 = new todoApp.ToDoItem("Sturmschaden", "mit der Hausverwaltung schnacken",3);


        List <ToDoItem> todos1 = List.of(newitem1, newitem2);

        when(toDoService.itemsByDeadline("28 02 2022")).thenReturn(todos1);
        ResponseEntity<ToDoItem[]> response = restTemplate.getForEntity("/todoapp/getbydate/28 02 2022", ToDoItem[].class);
        assertTrue(Arrays.stream(response.getBody()).count() == 2);
        assertTrue(Arrays.stream(response.getBody()).toList().equals(todos1));
        assertThat(response.getBody()).containsExactlyInAnyOrderElementsOf(todos1);
    }
}