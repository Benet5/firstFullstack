package todoApp;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class ToDoControllerTest {
    @Autowired

    private TestRestTemplate restTemplate;



    @Test
    void IntegrationstestPost() {

        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen",1);

        ResponseEntity<Void> response = restTemplate.postForEntity("/todoapp", newitem1, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<ToDoItem> response1 = restTemplate.getForEntity("/todoapp/getitembyname/Milch", ToDoItem.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(newitem1.getName(), response1.getBody().getName());
    }


    @Test
    void Integrationstestdelete(){
        ToDoItem newitem2 = new ToDoItem("Kaffee", "ganze Bohnen!",0);
        ToDoItem newitem1 = new todoApp.ToDoItem("Milch", "Milch einkaufen nochmal",0);
        ResponseEntity<Void> response = restTemplate.postForEntity("/todoapp", newitem2, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        ResponseEntity<Void> response1 = restTemplate.postForEntity("/todoapp", newitem1, Void.class);
        assertEquals(response1.getStatusCode(), HttpStatus.OK);


       restTemplate.delete("/todoapp/deleteitem/Milch", Void.class);

        ResponseEntity<ToDoItem[]> response3 = restTemplate.getForEntity("/todoapp/getallitems", ToDoItem[].class);
       assertTrue(response3.getBody().length == 2);
        // das ist dasgleiche wie
        var check = Arrays.stream(response3.getBody()).filter(e -> e.equals(newitem2)).findFirst();
        assertTrue(check.get().equals(newitem2));
        //das
        assertThat(response3.getBody()).contains(newitem2);


    }

    @Test
    void Integrationstestput(){
        ToDoItem newitem9 = new ToDoItem("Kino", "ganze Bohnen!",0);
        ToDoItem newitem8 = new todoApp.ToDoItem("Milch", "Milch einkaufen",0);
        ResponseEntity<Void> response = restTemplate.postForEntity("/todoapp", newitem9, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        ResponseEntity<Void> response1 = restTemplate.postForEntity("/todoapp", newitem8, Void.class);
        assertEquals(response1.getStatusCode(), HttpStatus.OK);
        // testen mit einem moc

        restTemplate.put("/todoapp/checkitem/Milch", newitem8, Void.class);
        restTemplate.put("/todoapp/checkitem/Kino", newitem9, Void.class);
        restTemplate.put("/todoapp/checkitem/Kino", newitem9, Void.class);


        ResponseEntity<ToDoItem> response6 = restTemplate.getForEntity("/todoapp/getitembyname/Milch", ToDoItem.class);
        ResponseEntity<ToDoItem> response4 = restTemplate.getForEntity("/todoapp/getitembyname/Kino", ToDoItem.class);
        assertTrue(response6.getBody().isStatus());
        assertFalse(response4.getBody().isStatus());
        }

    }


