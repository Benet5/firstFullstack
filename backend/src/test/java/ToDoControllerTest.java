import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest (webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDoControllerTest {
    @Autowired

    private TestRestTemplate restTemplate;

@Test
//@Order(1)
     void IntegrationstestPost(){
      List<ToDoItem> todo = new ArrayList<>();
      ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen");

        ResponseEntity <Void> response = restTemplate.postForEntity("/todoapp/addtodo", newitem1, Void.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
/*
    @Test
    @Order(2)
    void IntegrationstestGet(){
        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen");

    ResponseEntity <ToDoItem> response = restTemplate.getForEntity("/todoapp/getitembyname/Milch", ToDoItem.class);
        assertEquals(response.getBody(),newitem1);
    }
*/

}