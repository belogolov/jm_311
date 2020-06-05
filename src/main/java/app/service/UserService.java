package app.service;

import app.model.Role;
import app.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserService {
    List<User> listUsers();
    List<Role> listRoles();

    User getUserById(Long id);

    User getUserByEmail(String email);

    void add(User user);

    void delete(Long id);

    void update(User user);

}
