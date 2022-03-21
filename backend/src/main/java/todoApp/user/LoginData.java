package todoApp.user;

public class LoginData {

    private String email;
    private String password;
    private String passwordValidate;

    public String getPasswordValidate() {
        return passwordValidate;
    }


    public LoginData(String email, String password, String passwordValidate) {
        this.email = email;
        this.password = password;
        this.passwordValidate = passwordValidate;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
