package app.services;

import app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import app.repositories.UserRepository;

@Service
@Primary
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void registerUser(User user) {
        if (this.userRepository.getByUsername(user.getUsername()) != null) {
            throw new IllegalArgumentException("This user already exists in the database!");
        }
        this.userRepository.save(user);
    }

    @Override
    public User getByUsername(String username) {
        return this.userRepository.getByUsername(username);
    }
}
