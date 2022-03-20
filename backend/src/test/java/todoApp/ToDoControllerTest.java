package todoApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import todoApp.user.AppUser;
import todoApp.user.LoginData;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class ToDoControllerTest {
    @Autowired

    private TestRestTemplate restTemplate;



    @Test
    void IntegrationstestPost() {
        ResponseEntity<AppUser> createUserResponse = restTemplate.postForEntity("/todoapp/auth", new AppUser("test@email.de", "123456"), AppUser.class);
        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(createUserResponse.getBody().getEmail().equals("test@email.de"));

        ResponseEntity<String> loginResponse = restTemplate.postForEntity("/todoapp/auth/login", new LoginData("test@email.de", "123456"), String.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).isNotEmpty();


        ToDoItem newitem1 = new ToDoItem("Milch", "Milch einkaufen",1, null);

        ResponseEntity<ToDoItem> response = restTemplate.exchange("/todoapp", HttpMethod.POST,
                new HttpEntity<>(newitem1, createHeaders(loginResponse.getBody())),  ToDoItem.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals(newitem1.getName(), response.getBody().getName());

    }


    @Test
    void Integrationstestdelete(){
        //UserAnlage
        ResponseEntity<AppUser> createUserResponse = restTemplate.postForEntity("/todoapp/auth", new AppUser("test2@email.de", "123456"), AppUser.class);
        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(createUserResponse.getBody().getEmail().equals("test@email.de"));

        ResponseEntity<String> loginResponse = restTemplate.postForEntity("/todoapp/auth/login", new LoginData("test2@email.de", "123456"), String.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).isNotEmpty();


        ToDoItem newitem2 = new ToDoItem("Kaffee", "ganze Bohnen!",0, null);
        ToDoItem newitem1 = new todoApp.ToDoItem("Magermilch", "Milch einkaufen nochmal",0, null);


        //POST von 2 Items
        ResponseEntity<ToDoItem> response = restTemplate.exchange("/todoapp", HttpMethod.POST,
                new HttpEntity<>(newitem2, createHeaders(loginResponse.getBody())),  ToDoItem.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        ResponseEntity<ToDoItem> response2 = restTemplate.exchange("/todoapp", HttpMethod.POST,
                new HttpEntity<>(newitem1, createHeaders(loginResponse.getBody())),  ToDoItem.class);
        assertEquals(response2.getStatusCode(), HttpStatus.OK);

        //Delete
        ResponseEntity<Void> responseDel = restTemplate.exchange("/todoapp/deleteitem/Magermilch", HttpMethod.DELETE,
                new HttpEntity<>(createHeaders(loginResponse.getBody())),  Void.class);
        assertEquals(responseDel.getStatusCode(), HttpStatus.OK);

       // Zur Überprüfung
        ResponseEntity<ToDoItem[]> response3 = restTemplate.exchange("/todoapp/getallitems", HttpMethod.GET, new HttpEntity<>(createHeaders(loginResponse.getBody())), ToDoItem[].class);
        assertThat(response3.getBody()).contains(newitem2);
        // das ist dasgleiche wie
        var check = Arrays.stream(response3.getBody()).filter(e -> e.equals(newitem2)).findFirst();
        assertTrue(check.get().equals(newitem2));
        //das
     //   assertThat(response3.getBody()).contains(newitem2);


    }

    @Test
    void Integrationstestput(){

        //UserAnlage
        ResponseEntity<AppUser> createUserResponse = restTemplate.postForEntity("/todoapp/auth", new AppUser("test3@email.de", "123456"), AppUser.class);
        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(createUserResponse.getBody().getEmail().equals("test@email.de"));

        ResponseEntity<String> loginResponse = restTemplate.postForEntity("/todoapp/auth/login", new LoginData("test3@email.de", "123456"), String.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).isNotEmpty();

        ToDoItem newitem9 = new ToDoItem("Kaffeebohnen", "ganze Bohnen!",0, null);
        ToDoItem newitem8 = new todoApp.ToDoItem("Erbsen", "Erbsen einkaufen nochmal",0, null);

        //items anlegen
        ResponseEntity<ToDoItem> response = restTemplate.exchange("/todoapp", HttpMethod.POST,
                new HttpEntity<>(newitem9, createHeaders(loginResponse.getBody())),  ToDoItem.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        ResponseEntity<ToDoItem> response2 = restTemplate.exchange("/todoapp", HttpMethod.POST,
                new HttpEntity<>(newitem8, createHeaders(loginResponse.getBody())),  ToDoItem.class);
        assertEquals(response2.getStatusCode(), HttpStatus.OK);

        // Items checken
        ResponseEntity <Void> response5 = restTemplate.exchange("/todoapp/checkitem/Erbsen", HttpMethod.PUT,
                new HttpEntity<>(newitem8, createHeaders(loginResponse.getBody())),  Void.class);
        assertEquals(response5.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Void> response6 = restTemplate.exchange("/todoapp/checkitem/Kaffeebohnen", HttpMethod.PUT,
                new HttpEntity<>(newitem9, createHeaders(loginResponse.getBody())),  Void.class);
        assertEquals(response6.getStatusCode(), HttpStatus.OK);

        ResponseEntity<Void> response7 = restTemplate.exchange("/todoapp/checkitem/Kaffeebohnen", HttpMethod.PUT,
                new HttpEntity<>(newitem9, createHeaders(loginResponse.getBody())),  Void.class);
        assertEquals(response7.getStatusCode(), HttpStatus.OK);


        //Items Prüfen
        ResponseEntity<ToDoItem> response8 = restTemplate.exchange("/todoapp/getitembyname/Erbsen", HttpMethod.GET,
                new HttpEntity<>(createHeaders(loginResponse.getBody())), ToDoItem.class);
        ResponseEntity<ToDoItem> response9 = restTemplate.exchange("/todoapp/getitembyname/Kaffeebohnen", HttpMethod.GET,
                new HttpEntity<>(createHeaders(loginResponse.getBody())), ToDoItem.class);
        assertTrue(response8.getBody().isStatus());
        assertFalse(response9.getBody().isStatus());
        }

    private HttpHeaders createHeaders(String token){
        String authHeader = "Bearer " + token;
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);

        return headers;
    }


    }


