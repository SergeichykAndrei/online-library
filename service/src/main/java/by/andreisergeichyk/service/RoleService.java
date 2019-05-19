package by.andreisergeichyk.service;

import by.andreisergeichyk.entity.Role;
import by.andreisergeichyk.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role role) {
        return roleRepository.save(role);
    }

    public void delete(Role role) {
        roleRepository.delete(role);
    }

    public Role findById(Integer id) {
        return roleRepository.findById(id).orElse(null);
    }

    public List<Role> findAll() {
        Iterable<Role> result = roleRepository.findAll();
        ArrayList<Role> roles = new ArrayList<>();
        result.forEach(roles::add);

        return roles;
    }
}
