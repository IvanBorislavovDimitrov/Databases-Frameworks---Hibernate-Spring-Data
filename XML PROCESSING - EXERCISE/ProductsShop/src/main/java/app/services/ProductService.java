package app.services;

import app.entities.Category;
import app.entities.Product;
import app.entities.User;
import app.entities.dto.ProductInRangeContainer;
import app.entities.dto.ProductsInRangeDto;
import app.entities.dto.for_xml.product.ProductContainer;
import app.entities.dto.for_xml.product.ProductDto;
import app.repositories.ProductRepo;
import app.serializer.XmlSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import app.entities.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
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
    private final XmlSerializer xmlSerializer;
    private final ModelMapper mapper;

    @Autowired
    public ProductService(ProductRepo productRepo, UserService userService, CategoryService categoryService,
                          XmlSerializer xmlSerializer) {
        this.productRepo = productRepo;
        this.userService = userService;
        this.categoryService = categoryService;
        this.xmlSerializer = xmlSerializer;
        this.mapper = new ModelMapper();
    }

    public void importProductsFromJsonFile(String fileName) throws JAXBException {
        ProductContainer productContainer = this.xmlSerializer.deserialize(ProductContainer.class, fileName);

        List<User> users = this.userService.findAll();
        Random random = new Random();
        int cnt = 0;
        for (ProductDto productDto : productContainer.getProducts()) {
            Product product = this.mapper.map(productDto, Product.class);
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

    public void productsInRange(String fileName) throws IOException, JAXBException {
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

        ProductInRangeContainer prc = new ProductInRangeContainer();
        prc.setProductsInRangeDtos(productsInRangeDtos);

        this.xmlSerializer.serialize(prc, fileName);
    }
}
