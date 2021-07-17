package pers.jysh.pcshop.data;

import org.springframework.data.repository.CrudRepository;
import pers.jysh.pcshop.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

}
