package app.entities.dto.successfully_sold_products_dtos;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "users")
public class UsersContainerSuccessfullySold {

    @XmlElement(name = "user")
    private List<UserWithSoldItems> usersWithSoldItems;

    public UsersContainerSuccessfullySold() {
        this.usersWithSoldItems = new ArrayList<>();
    }

    public List<UserWithSoldItems> getUsersWithSoldItems() {
        return usersWithSoldItems;
    }

    public void setUsersWithSoldItems(List<UserWithSoldItems> usersWithSoldItems) {
        this.usersWithSoldItems = usersWithSoldItems;
    }
}
