package app.terminal;

import app.services.CategoryService;
import app.services.ProductService;
import app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
@Transactional // for lazy fetching
public class Terminal implements CommandLineRunner {

    private static final String USER_FILE_JSON = "/input/users.json";
    private static final String CATEGORIES_FILE_JSON = "/input/categories.json";
    private static final String PRODUCTS_FILE_JSON = "/input/products.json";
    private static final String PRODUCTS_IN_RANGE = "products_in_range.json";
    private static final String SUCCESSFULLY_SOLD_PRODUCTS = "SUCCESSFULLY_SOLD_PRODUCTS.json";
    private static final String CATEGORIES_BY_PRODUCTS_COUNT = "CATEGORIES_BY_PRODUCTS_COUNT.json";
    private static final String USERS_AND_PRODUCTS = "USERS_AND_PRODUCTS.json";


    private final UserService userService;
    private final CategoryService categoryService;
    private final ProductService productService;

    @Autowired
    public Terminal(UserService userService, CategoryService categoryService, ProductService productService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.productService = productService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedTheDatabase();
//        productsInRange();
//        successfullySoldProducts();
//        categoriesByProductsCount();
        usersAndProducts();
    }

    private void usersAndProducts() {
        this.userService.usersAndProducts(USERS_AND_PRODUCTS);
    }

    private void categoriesByProductsCount() {
        this.categoryService.categoriesByProductsCount(CATEGORIES_BY_PRODUCTS_COUNT);
    }

    private void successfullySoldProducts() throws IOException {
        this.userService.successfullySoldProducts(SUCCESSFULLY_SOLD_PRODUCTS);
    }

    private void productsInRange() throws IOException {
        this.productService.productsInRange(PRODUCTS_IN_RANGE);
    }

    private void seedTheDatabase() {
        importUsersFromJsonFile();
        importCategoriesFromJsonFile();
        importProductsFromJsonFile();
    }

    private void importProductsFromJsonFile() {
        this.productService.importProductsFromJsonFile(PRODUCTS_FILE_JSON);
    }

    private void importCategoriesFromJsonFile() {
        this.categoryService.importCategoriesFromJsonFile(CATEGORIES_FILE_JSON);
    }

    private void importUsersFromJsonFile() {
        this.userService.importUsers(USER_FILE_JSON);
    }
}
