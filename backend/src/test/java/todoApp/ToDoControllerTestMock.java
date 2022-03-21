package todoApp;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import todoApp.user.AppUser;
import todoApp.user.LoginData;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDoControllerTestMock {
    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
   ToDoRepo repo;

    @Test
    void ShouldGetAllItems() {
        //UserAnlage
        ResponseEntity<AppUser> createUserResponse = restTemplate.postForEntity("/todoapp/auth", new LoginData("test@email.de", "123456", "123456"), AppUser.class);
        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(createUserResponse.getBody().getEmail().equals("test@email.de"));

        ResponseEntity<String> loginResponse = restTemplate.postForEntity("/todoapp/auth/login", new LoginData("test@email.de", "123456", "123456"), String.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).isNotEmpty();

        //Mocken Principal
        Principal p = Mockito.mock(Principal.class);
        Mockito.when(p.getName()).thenReturn("test@email.de");

        ToDoItem newitem1 = new ToDoItem("Kaffee", "ganze Bohnen!", 2, "test@email.de");
        ToDoItem newitem2 = new ToDoItem("Tanken", "maximal bis 50€", 2, "test@email.de");
        ToDoItem newitem3 = new ToDoItem("KiTa-Platz", "klären bzgl frühestmöglicher Anmeldung", 0, "test@email.de");
        ToDoItem newitem4 = new ToDoItem("Sturmschaden", "mit der Hausverwaltung schnacken", 0, "test@email.de");

        List<ToDoItem> todos = List.of(newitem3, newitem4);

        ResponseEntity<ToDoItem> response = restTemplate.exchange("/todoapp", HttpMethod.POST,
                new HttpEntity<>(newitem3, createHeaders(loginResponse.getBody())),  ToDoItem.class);
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        when(repo.save(newitem4)).thenReturn(newitem4);
        ResponseEntity<ToDoItem> response2 = restTemplate.exchange("/todoapp", HttpMethod.POST,
                new HttpEntity<>(newitem4, createHeaders(loginResponse.getBody())),  ToDoItem.class);
        assertEquals(response2.getStatusCode(), HttpStatus.OK);
        assertThat(response2.getBody().equals(newitem4));



        when(repo.findAllByUserMail("test@email.de")).thenReturn(todos);
        ResponseEntity<ToDoItem[]> responseSec = restTemplate.exchange("/todoapp/getallitems", HttpMethod.GET,
                new HttpEntity<>(createHeaders(loginResponse.getBody())), ToDoItem[].class);
        assertThat(responseSec.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertTrue(responseSec.getBody().length ==2);



        List <ToDoItem> todos1 = List.of(newitem1, newitem2);

        when(repo.findAllByFormattedEndDateAndUserMail("20 03 2022","test@email.de")).thenReturn(todos1);
        ResponseEntity<ToDoItem[]> responseThird = restTemplate.exchange("/todoapp/getbydate/20 03 2022", HttpMethod.GET,
                new HttpEntity<>(createHeaders(loginResponse.getBody())),ToDoItem[].class);
        assertEquals(Arrays.stream(responseThird.getBody()).count(),2);
        assertTrue(Arrays.stream(responseThird.getBody()).toList().equals(todos1));
        assertThat(responseThird.getBody()).containsExactlyInAnyOrderElementsOf(todos1);




    }

        private HttpHeaders createHeaders(String token){
            String authHeader = "Bearer " + token;
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", authHeader);

            return headers;
        }



}