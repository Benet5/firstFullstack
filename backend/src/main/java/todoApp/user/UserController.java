package todoApp.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/todoapp/auth")
public class UserController {

    private final AppUserService service;
    private final PasswordEncoder passwordEncoder;

    public UserController(AppUserService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public AppUser createUser(@RequestBody AppUser user) throws RuntimeException{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return service.createUser(user);
    }

    @GetMapping("/greets")
    public String hello(Principal principal){
        return "hello"+ principal.getName();
    }

}
