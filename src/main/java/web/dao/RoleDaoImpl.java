package web.dao;

import org.springframework.stereotype.Repository;
import web.models.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class RoleDaoImpl implements RoleDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Role addRole(Role role) {
        entityManager.persist(role);
        return role;
    }

    @Override
    public Role getRoleByRoleName(String role) {
        TypedQuery<Role> query = (TypedQuery<Role>) entityManager.createNativeQuery("SELECT * FROM mydbtest.roles where role= :role", Role.class);
        query.setParameter("role", role);
        return query.getSingleResult();
    }
}
