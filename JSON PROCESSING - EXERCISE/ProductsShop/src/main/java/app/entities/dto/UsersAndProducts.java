package app.entities.dto;

import java.util.ArrayList;
import java.util.List;

public class UsersAndProducts {

    private int countOfUsers;
    private List<UserDetails> users;

    public UsersAndProducts() {
        this.users = new ArrayList<>();
    }

    public UsersAndProducts(int countOfUsers, List<UserDetails> users) {
        this.countOfUsers = countOfUsers;
        this.users = users;
    }

    public int getCountOfUsers() {
        return countOfUsers;
    }

    public void setCountOfUsers(int countOfUsers) {
        this.countOfUsers = countOfUsers;
    }

    public List<UserDetails> getUsers() {
        return users;
    }

    public void setUsers(List<UserDetails> users) {
        this.users = users;
    }
}
