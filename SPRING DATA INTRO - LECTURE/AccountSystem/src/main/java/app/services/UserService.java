package app.services;

import app.models.User;

public interface UserService {

    void registerUser(User user);
    User getByUsername(String username);
}
