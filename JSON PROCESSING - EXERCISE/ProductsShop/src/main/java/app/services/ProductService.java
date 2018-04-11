package app.services;

import app.entities.Category;
import app.entities.Product;
import app.entities.User;
import app.entities.dto.ProductsInRangeDto;
import app.repositories.ProductRepo;
import app.serializer.JsonSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

@Service
@Transactional
public class ProductService {

    private final ProductRepo productRepo;
    private final UserService userService;
    private final CategoryService categoryService;
    private final JsonSerializer jsonSerializer;
    private final ModelMapper mapper;

    @Autowired
    public ProductService(ProductRepo productRepo, UserService userService, CategoryService categoryService, JsonSerializer jsonSerializer) {
        this.productRepo = productRepo;
        this.userService = userService;
        this.categoryService = categoryService;
        this.jsonSerializer = jsonSerializer;
        this.mapper = new ModelMapper();
    }

    public void importProductsFromJsonFile(String fileName) {

        Product[] products = this.jsonSerializer.deserialize(Product[].class, fileName);
        List<User> users = this.userService.findAll();
        Random random = new Random();
        int cnt = 0;
        for (Product product : products) {
            if (cnt != 10 && cnt != 9 && cnt != 20 && cnt != 4) {
                int userId = random.nextInt(users.size());
                product.setBuyer(users.get(userId));
            }
            int userId = random.nextInt(users.size());
            product.setSeller(users.get(userId));
            cnt++;
            this.productRepo.saveAndFlush(product);
        }
        fillCategoriesProducts();
    }

    private void fillCategoriesProducts() {
        List<Product> products = this.productRepo.findAll();
        List<Category> categories = this.categoryService.findAll();
        Random random = new Random();
        for (Product product : products) {
            int categoryId = random.nextInt(categories.size());
            Category category = categories.get(categoryId);
            category.getProducts().add(product);
            product.getCategories().add(category);
        }
        this.productRepo.saveAll(products);
    }

    public void productsInRange(String fileName) throws IOException {
        List<Product> productsWithoutBuyers = this.productRepo.getProductByBuyerIsNull();
        List<ProductsInRangeDto> productsInRangeDtos = new ArrayList<>(productsWithoutBuyers.size());

        for (Product product : productsWithoutBuyers) {
            if (product.getPrice() > 500 && product.getPrice() < 1000) {
                ProductsInRangeDto productsInRangeDto = this.mapper.map(product, ProductsInRangeDto.class);
                productsInRangeDto.setSeller(product.getSeller().getFirstName() + " " +
                                        product.getSeller().getLastName());
                productsInRangeDtos.add(productsInRangeDto);
            }
        }
        productsInRangeDtos.sort(Comparator.comparingDouble(ProductsInRangeDto::getPrice));

        this.jsonSerializer.serialize(productsInRangeDtos, fileName);
    }
}
