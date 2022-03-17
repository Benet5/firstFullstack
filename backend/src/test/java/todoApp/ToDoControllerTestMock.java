package todoApp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import todoApp.user.AppUser;
import todoApp.user.LoginData;

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
    void ShouldGetAllItems() {

        ResponseEntity<AppUser> createUserResponse = restTemplate.postForEntity("/todoapp/auth", new AppUser("test@email.de", "123456"), AppUser.class);
        assertThat(createUserResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(createUserResponse.getBody().getEmail().equals("test@email.de"));

        ResponseEntity<String> loginResponse = restTemplate.postForEntity("/todoapp/auth/login", new LoginData("test@email.de", "123456"), String.class);
        assertThat(loginResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(loginResponse.getBody()).isNotEmpty();


        ToDoItem newitem1 = new ToDoItem("Kaffee", "ganze Bohnen!", 0);
        ToDoItem newitem2 = new ToDoItem("Tanken", "maximal bis 50€", 0);
        ToDoItem newitem3 = new ToDoItem("KiTa-Platz", "klären bzgl frühestmöglicher Anmeldung", 0);
        ToDoItem newitem4 = new ToDoItem("Sturmschaden", "mit der Hausverwaltung schnacken", 0);

        List<ToDoItem> todos = List.of(newitem1, newitem2, newitem3, newitem4);

        when(toDoService.getAllItems()).thenReturn(todos);
        ResponseEntity<ToDoItem[]> responseSec = restTemplate.exchange("/todoapp/getallitems", HttpMethod.GET,
                new HttpEntity<>(createHeaders(loginResponse.getBody())), ToDoItem[].class);
        assertThat(responseSec.getStatusCode()).isEqualTo(HttpStatus.OK);



        List <ToDoItem> todos1 = List.of(newitem1, newitem2);

        when(toDoService.itemsByDeadline("28 02 2022")).thenReturn(todos1);
        ResponseEntity<ToDoItem[]> responseThird = restTemplate.exchange("/todoapp/getbydate/28 02 2022", HttpMethod.GET,
                new HttpEntity<>(createHeaders(loginResponse.getBody())),ToDoItem[].class);
        assertTrue(Arrays.stream(responseThird.getBody()).count() == 2);
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