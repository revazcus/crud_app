package web.dao;

import web.models.Role;

public interface RoleDao {
    Role addRole(Role role);
    Role getRoleByRoleName(String role);
}
