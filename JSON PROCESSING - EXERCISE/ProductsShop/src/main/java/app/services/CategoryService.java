package app.services;

import app.entities.Category;
import app.entities.Product;
import app.entities.dto.CategoryByProduct;
import app.repositories.CategoryRepo;
import app.serializer.JsonSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final JsonSerializer jsonSerializer;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo, JsonSerializer jsonSerializer) {
        this.categoryRepo = categoryRepo;
        this.jsonSerializer = jsonSerializer;
    }

    public void importCategoriesFromJsonFile(String fileName) {
        Category[] categories = this.jsonSerializer.deserialize(Category[].class, fileName);
        for (Category category : categories) {
            this.categoryRepo.save(category);
        }
    }

    public List<Category> findAll() {
        return this.categoryRepo.findAll();
    }

    public Category findById(Long id) {
        return this.categoryRepo.findFirstById(id);
    }

    public void save(Category category) {
        this.categoryRepo.saveAndFlush(category);
    }

    public void deleteAll() {
        this.categoryRepo.deleteAll();
    }

    public void saveAll(List<Category> categories) {
        this.categoryRepo.saveAll(categories);
    }

    public void categoriesByProductsCount(String fileName) {
        List<Category> allCategories = this.categoryRepo.findAll();
        CategoryByProduct[] categoryByProducts = new CategoryByProduct[allCategories.size()];
        int cnt = 0;
        for (Category category : allCategories) {
            String categoryName = category.getName();
            int countOfProducts = category.getProducts().size();
            double avrOfPrices = category.getProducts()
                    .stream()
                    .mapToDouble(Product::getPrice)
                    .average()
                    .getAsDouble();

            double sumOfPrices = category.getProducts()
                    .stream()
                    .mapToDouble(Product::getPrice)
                    .sum();
            CategoryByProduct categoryByProduct = new CategoryByProduct(categoryName, countOfProducts,
                    avrOfPrices, sumOfPrices);
            categoryByProducts[cnt++] = categoryByProduct;
        }
        Arrays.sort(categoryByProducts, (x1, x2) -> Integer.compare(x2.getProductsCount(), x1.getProductsCount()));
        try {
            this.jsonSerializer.serialize(categoryByProducts, fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
