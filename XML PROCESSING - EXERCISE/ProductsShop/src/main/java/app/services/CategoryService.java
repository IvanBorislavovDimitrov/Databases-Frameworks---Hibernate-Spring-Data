package app.services;

import app.entities.Category;
import app.entities.Product;
import app.entities.dto.for_xml.categories_by_products_count.CategoriesByProductsDto;
import app.entities.dto.for_xml.categories_by_products_count.CategoryByProduct;
import app.entities.dto.for_xml.category.CategoriesContainer;
import app.entities.dto.for_xml.category.CategoryDto;
import app.repositories.CategoryRepo;
import app.serializer.XmlSerializer;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class CategoryService {

    private final CategoryRepo categoryRepo;
    private final XmlSerializer xmlSerializer;
    private final ModelMapper mapper;

    @Autowired
    public CategoryService(CategoryRepo categoryRepo, XmlSerializer xmlSerializer) {
        this.categoryRepo = categoryRepo;
        this.xmlSerializer = xmlSerializer;
        this.mapper = new ModelMapper();
    }

    public void importCategoriesFromJsonFile(String fileName) throws JAXBException {
        CategoriesContainer categoriesContainer = this.xmlSerializer.deserialize(CategoriesContainer.class, fileName);
        for (CategoryDto category : categoriesContainer.getCategories()) {
            this.categoryRepo.save(this.mapper.map(category, Category.class));
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
            CategoriesByProductsDto categoriesByProductsDto = new CategoriesByProductsDto();
            List<CategoryByProduct> categoryByProductList = new ArrayList<>(Arrays.asList(categoryByProducts));

            categoriesByProductsDto.setCategoriesByProduct(categoryByProductList);
            this.xmlSerializer.serialize(categoriesByProductsDto, fileName);
        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }
}
