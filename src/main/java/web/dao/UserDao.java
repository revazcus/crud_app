package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    User addUser(User user);
    List<User> getAllUsers();
    void deleteUserById(Long id);
    void updateUser(Long id, User updateUser);
    User findById (Long id);
    User getUserByUsername(String username);

}
