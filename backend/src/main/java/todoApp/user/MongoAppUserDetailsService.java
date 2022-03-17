package todoApp.user;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MongoAppUserDetailsService implements UserDetailsService {
    private final AppUserService service;

    public MongoAppUserDetailsService(AppUserService service) {
        this.service = service;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return service.findByEmail(email)
                .map(gotUser -> new User(gotUser.getEmail(),gotUser.getPassword(), List.of( new SimpleGrantedAuthority("ROLE_" +gotUser.getRole()))))
                .orElseThrow (() -> new UsernameNotFoundException("Dieser User existiert nicht" ));
    }




}
