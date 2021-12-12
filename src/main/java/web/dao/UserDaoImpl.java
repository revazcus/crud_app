package web.dao;


import org.springframework.stereotype.Repository;
import web.models.User;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public User addUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        TypedQuery<User> query = (TypedQuery<User>) entityManager.createNativeQuery("SELECT * from mydbtest.users", User.class);
        return query.getResultList();
    }

    @Override
    public void deleteUserById(Long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void updateUser(Long id, User updateUser) {
        User user = findById(id);
        user.setUsername(updateUser.getUsername());
        user.setPassword(updateUser.getPassword());
        user.setRoles(updateUser.getRoles());
        entityManager.merge(user);

    }

    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    @Override
    public User getUserByUsername(String username) {
        TypedQuery<User> query = (TypedQuery<User>) entityManager.createNativeQuery("SELECT * FROM mydbtest.users where username = :username", User.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }

}
