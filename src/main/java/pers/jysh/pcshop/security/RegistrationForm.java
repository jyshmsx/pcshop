package pers.jysh.pcshop.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import pers.jysh.pcshop.User;

@Data
public class RegistrationForm {
    private String username;
    private String password;
    private String fullname;

    /**
     * 加密user
     * @param passwordEncoder 使用的加密方式
     * @return 返回对this的password加密后的User
     */
    public User toUser(PasswordEncoder passwordEncoder){
        return new User(username, passwordEncoder.encode(password), fullname);
    }
}
