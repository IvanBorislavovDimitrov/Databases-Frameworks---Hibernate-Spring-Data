package app.entities.dto.for_xml.users_and_products;


import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class UsersAndProducts {

    @XmlAttribute(name = "count")
    private int countOfUsers;

    @XmlElement(name = "user")
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