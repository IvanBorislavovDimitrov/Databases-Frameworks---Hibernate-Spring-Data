package app.service_imp;

import app.models.entities.Role;
import app.models.entities.User;
import app.repositories.RoleRepository;
import app.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImp implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void initializeRoles() {
        if (this.roleRepository.count() == 0) {
            Role user = new Role();
            user.setName("User");
            Role administrator = new Role();
            administrator.setName("Administrator");

            this.roleRepository.saveAndFlush(administrator);
            this.roleRepository.saveAndFlush(user);
        }
    }

    @Override
    public Role getByName(String name) {
        return this.roleRepository.getByName(name);
    }
}
