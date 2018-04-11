package app.services;

import app.entities.Product;
import app.entities.User;
import app.entities.dto.*;
import app.repositories.UserRepo;
import app.serializer.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepo userRepo;
    private final JsonSerializer jsonSerializer;

    @Autowired
    public UserService(UserRepo userRepo, JsonSerializer jsonSerializer) {
        this.userRepo = userRepo;
        this.jsonSerializer = jsonSerializer;
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
            this.jsonSerializer.serialize(usersAndProducts, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void successfullySoldProducts(String fileName) throws IOException {
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
                seller.getSoldProducts().add(soldProduct);
            }
            usersWithSoldItems.add(seller);
        }
        this.jsonSerializer.serialize(usersWithSoldItems, fileName);
    }

    public void importUsers(String fileName) {
        User[] users = this.jsonSerializer.deserialize(User[].class, fileName);
        for (User user : users) {
            this.userRepo.save(user);
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
