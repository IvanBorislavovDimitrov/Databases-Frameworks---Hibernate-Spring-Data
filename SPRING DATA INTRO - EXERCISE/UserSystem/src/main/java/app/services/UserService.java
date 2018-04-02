package app.services;

import app.entities.User;
import app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return this.userRepository.findAll();
    }

    public void removeInactiveUsers(Timestamp date) {
        this.userRepository.removeInactiveUsers(date);
    }

    public void register(User user) {
        this.userRepository.saveAndFlush(user);
    }

    public void deleteAllByIsDeleted(int isDeleted) {
        this.userRepository.deleteAllByIsDeleted(isDeleted);
    }
}
