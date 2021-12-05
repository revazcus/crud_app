package web.service;

import web.models.User;

import java.util.List;

public interface UserService {
    User addUser(User user);
    List<User> getAllUsers();
    void deleteUserById(long id);
    void updateUser(long id, User updateUser);;
    User findById (long id);
}
