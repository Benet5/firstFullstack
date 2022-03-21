package todoApp.user;


import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    private final AppUserRepo repo;


    public AppUserService(AppUserRepo repo) {
        this.repo = repo;
    }

    public AppUser createUser(LoginData loginData) throws RuntimeException{
        if(repo.findByEmail(loginData.getEmail()).isEmpty()) {
            AppUser user =new AppUser();
            user.setPassword(loginData.getPassword());
            user.setEmail(loginData.getEmail());
            return repo.save(user);
        } else {
            throw new RuntimeException("Fehler bei der Accountanlage");
        }
    }

    public Optional<AppUser> findByEmail(String email){
        return repo.findByEmail(email);
    }


}
