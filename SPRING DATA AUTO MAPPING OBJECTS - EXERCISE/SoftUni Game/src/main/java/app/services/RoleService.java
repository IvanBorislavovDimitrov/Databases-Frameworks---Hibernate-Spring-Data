package app.services;

import app.models.entities.Role;
import app.models.entities.User;

public interface RoleService {

    void initializeRoles();

    Role getByName(String name);


}
