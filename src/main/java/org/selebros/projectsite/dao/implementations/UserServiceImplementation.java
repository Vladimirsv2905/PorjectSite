package org.selebros.projectsite.dao.implementations;

import lombok.RequiredArgsConstructor;
import org.selebros.projectsite.dao.interfaces.UserService;
import org.selebros.projectsite.security.dao.UserRepository;
import org.selebros.projectsite.security.entity.Role;
import org.selebros.projectsite.security.entity.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository repo;
    private final PasswordEncoder encoder;
    @Override
    public List<User> all() {
        return repo.findAll();
        //return null;
    }

    @Override
    public Optional<User> findById(int id) {
        return findById(id);
        //return Optional.empty();
    }

    @Override
    public User save(User user) {
       if (repo.findByUsername(user.getUsername()).isEmpty()) {
           user.setPassword(encoder.encode(user.getPassword()));
           user.setRole(Role.ROLE_USER);
           return repo.save(user);
       }
       return null;

    }

    @Override
    public User update(User user) {
        User userToUpdate = repo.findById(user.getId()).get();
        userToUpdate.setUsername(user.getUsername());
        userToUpdate.setRole(user.getRole());
        return repo.save(userToUpdate);
    }
       // return null;


    @Override
    public boolean deleteById(int id) {
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return true;
        }
        return false;
    }
}
