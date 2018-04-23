package app.exam.service.imp;

import app.exam.domain.dto.xml.CategoriesFrequentItemsXMLExportDTO;
import app.exam.domain.dto.xml.CategoryExportDTO;
import app.exam.domain.dto.xml.MostPopularItemDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.domain.entities.OrderItem;
import app.exam.repository.CategoryRepository;
import app.exam.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImp implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoriesFrequentItemsXMLExportDTO getCategoriesWithMostPopularItems(List<String> categoryNames) {
        List<Category> categoryList = this.categoryRepository
                .findAll()
                .stream()
                .filter(x -> categoryNames.contains(x.getName()))
                .collect(Collectors.toList());

        CategoriesFrequentItemsXMLExportDTO categories = new CategoriesFrequentItemsXMLExportDTO();

        for (Category category : categoryList) {
            CategoryExportDTO categoryExportDTO = new CategoryExportDTO();
            categoryExportDTO.setName(category.getName());
            MostPopularItemDTO mostPopularItemDTO = new MostPopularItemDTO();
            mostPopularItemDTO.setName(new ArrayList<>(category.getItems()).get(0).getName());
            mostPopularItemDTO.setTotalMade(BigDecimal.ZERO);

            for (Item item : category.getItems()) {
                BigDecimal price = BigDecimal.ZERO;
                int times = 0;
                for (OrderItem orderItem : item.getOrderItems()) {
                    times += orderItem.getQuantity();
                }
                price = price.add(new BigDecimal((new BigDecimal(times).multiply(item.getPrice()).toString())));
                if (mostPopularItemDTO.getTotalMade().compareTo(price) < 0) {
                    mostPopularItemDTO.setTotalMade(price);
                    mostPopularItemDTO.setTimesSold(times);
                }
                categoryExportDTO.setMostPopularItemDTO(mostPopularItemDTO);
            }
            categories.getCategories().add(categoryExportDTO);
        }
        categories.getCategories().sort((x2, x1) -> {
            if (x1.getMostPopularItemDTO().getTotalMade().compareTo(x2.getMostPopularItemDTO().getTotalMade()) == 0) {
                return Integer.compare(x1.getMostPopularItemDTO().getTimesSold(), x2.getMostPopularItemDTO().getTimesSold());
            }

            return x1.getMostPopularItemDTO().getTotalMade().compareTo(x2.getMostPopularItemDTO().getTotalMade());
        });
        return categories;
    }
}
