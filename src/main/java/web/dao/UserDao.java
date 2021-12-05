package web.dao;

import web.models.User;

import java.util.List;

public interface UserDao {
    User addUser(User user);
    List<User> getAllUsers();
    void deleteUserById(long id);
    void updateUser(long id, User updateUser);
    User findById (long id);

}
