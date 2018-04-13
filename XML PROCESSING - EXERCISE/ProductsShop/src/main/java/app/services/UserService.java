package app.services;

import app.entities.Product;
import app.entities.User;
import app.entities.dto.for_xml.user.UserDto;
import app.entities.dto.for_xml.user.UsersContainer;
import app.entities.dto.for_xml.users_and_products.ProductShortInfo;
import app.entities.dto.for_xml.users_and_products.SoldProducts;
import app.entities.dto.for_xml.users_and_products.UserDetails;
import app.entities.dto.for_xml.users_and_products.UsersAndProducts;
import app.entities.dto.successfully_sold_products_dtos.SoldProduct;
import app.entities.dto.successfully_sold_products_dtos.UserWithSoldItems;
import app.entities.dto.successfully_sold_products_dtos.UsersContainerSuccessfullySold;
import app.repositories.UserRepo;
import app.serializer.XmlSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepo userRepo;
    private final XmlSerializer xmlSerializer;
    private final ModelMapper mapper;

    @Autowired
    public UserService(UserRepo userRepo, XmlSerializer xmlSerializer) {
        this.userRepo = userRepo;
        this.xmlSerializer = xmlSerializer;
        this.mapper = new ModelMapper();
    }

    public void usersAndProducts(String fileName) {
        List<User> usersWithAtLeastOneSoldItem = this.userRepo.findAll()
                .stream().filter(x -> x.getProductsForSale().size() > 1).collect(Collectors.toList());
        UsersAndProducts usersAndProducts = new UsersAndProducts();

        usersAndProducts.setCountOfUsers(usersWithAtLeastOneSoldItem.size());
        for (User user : usersWithAtLeastOneSoldItem) {
            UserDetails userDetails = new UserDetails();
            userDetails.setFirstName(user.getFirstName());
            userDetails.setLastName(user.getLastName());
            userDetails.setAge(user.getAge());
            SoldProducts soldProducts = new SoldProducts();
            soldProducts.setProductsCount(user.getProductsForSale().size());
            for (Product product : user.getProductsForSale()) {
                ProductShortInfo productShortInfo = new ProductShortInfo(product.getName(), product.getPrice());
                soldProducts.getProducts().add(productShortInfo);
            }
            userDetails.setSoldProducts(soldProducts);
            usersAndProducts.getUsers().add(userDetails);
        }
        usersAndProducts.getUsers().sort((x1, x2) -> {
            if (x1.getSoldProducts().getProducts().size() == x2.getSoldProducts().getProducts().size()) {
                return x1.getLastName().compareTo(x2.getLastName());
            }

            return Integer.compare(x1.getSoldProducts().getProducts().size(), x2.getSoldProducts().getProducts().size());
        });
        try {
            this.xmlSerializer.serialize(usersAndProducts, fileName);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public void successfullySoldProducts(String fileName) throws IOException, JAXBException {
        List<User> users = this.userRepo.findAll();
        List<User> usersWithSoldItemsAndBuyers = new ArrayList<>();
        for (User user : users) {
            Set<Product> products = new HashSet<>();
            if (user.getProductsForSale().size() > 1) {
                for (Product product : user.getProductsForSale()) {
                    if (product.getBuyer() != null) {
                        products.add(product);
                    }
                }
                user.setProductsForSale(products);
                if (user.getProductsForSale().size() > 1) {
                    usersWithSoldItemsAndBuyers.add(user);
                }
            }
        }
        usersWithSoldItemsAndBuyers.sort((x1, x2) -> {
            if (x1.getLastName().equals(x2.getLastName())) {
                if (x1.getFirstName() == null || x2.getFirstName() == null) {
                    return 0;
                }
                return x1.getFirstName().compareTo(x2.getFirstName());
            }

            return x1.getLastName().compareTo(x2.getLastName());
        });
        List<UserWithSoldItems> usersWithSoldItems = new ArrayList<>();
        for (User user : usersWithSoldItemsAndBuyers) {
            UserWithSoldItems seller = new UserWithSoldItems();
            seller.setFirstName(user.getFirstName());
            seller.setLastName(user.getLastName());
            for (Product product : user.getProductsForSale()) {
                SoldProduct soldProduct = new SoldProduct();
                soldProduct.setProductName(product.getName());
                soldProduct.setPrice(product.getPrice());
                soldProduct.setBuyerFirstName(product.getBuyer().getFirstName());
                soldProduct.setBuyerLastName(product.getBuyer().getLastName());
                seller.getSoldProducts().getSoldProducts().add(soldProduct);
            }
            usersWithSoldItems.add(seller);
        }
        UsersContainerSuccessfullySold usersContainerSuccessfullySold = new UsersContainerSuccessfullySold();
        usersContainerSuccessfullySold.setUsersWithSoldItems(usersWithSoldItems);

        this.xmlSerializer.serialize(usersContainerSuccessfullySold, fileName);
    }

    public void importUsers(String fileName) throws JAXBException {
        UsersContainer usersContainer = this.xmlSerializer.deserialize(UsersContainer.class, fileName);
        String stop = null;
        for (UserDto user : usersContainer.getUsers()) {
            User fullUser = this.mapper.map(user, User.class);
            this.userRepo.save(fullUser);
        }
        fillUsersFriends();
    }

    public List<User> findAll() {
        return this.userRepo.findAll();
    }

    public void fillUsersFriends() {
        List<User> users = this.findAll();
        Random random = new Random();
        for (User user : users) {
            user.getFriends().add(users.get(random.nextInt(users.size())));
            user.getFriends().add(users.get(random.nextInt(users.size())));
            user.getFriends().add(users.get(random.nextInt(users.size())));
            user.getFriends().add(users.get(random.nextInt(users.size())));
            user.getFriends().add(users.get(random.nextInt(users.size())));
            user.getFriends().add(users.get(random.nextInt(users.size())));
            user.getFriends().add(users.get(random.nextInt(users.size())));
            user.getFriends().add(users.get(random.nextInt(users.size())));
            user.getFriends().add(users.get(random.nextInt(users.size())));
        }

        this.userRepo.saveAll(users);
    }
}
