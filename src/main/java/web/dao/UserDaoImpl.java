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
    public void deleteUserById(long id) {
        User user = entityManager.find(User.class, id);
        entityManager.remove(user);
    }

    @Override
    public void updateUser(long id, User updateUser) {
        User user = findById(id);
        user.setName(updateUser.getName());
        user.setSurname(updateUser.getSurname());
        user.setAge(updateUser.getAge());
        entityManager.merge(user);

    }

    @Override
    public User findById(long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }
}
