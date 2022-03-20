package todoApp.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepo repo;


    public AppUserService(AppUserRepo repo) {
        this.repo = repo;
    }

    public AppUser createUser(AppUser user) throws RuntimeException{
        if(repo.findByEmail(user.getEmail()).isEmpty()) {
            return repo.save(user);
        } else {
            throw new RuntimeException("Fehler bei der Accountanlage");
        }
    }

    public Optional<AppUser> findByEmail(String email){
        return repo.findByEmail(email);
    }


}
