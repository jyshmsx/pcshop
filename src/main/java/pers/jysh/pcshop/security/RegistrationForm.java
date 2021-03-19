package pers.jysh.pcshop.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import pers.jysh.pcshop.User;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;

    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password), fullname);
    }
}
