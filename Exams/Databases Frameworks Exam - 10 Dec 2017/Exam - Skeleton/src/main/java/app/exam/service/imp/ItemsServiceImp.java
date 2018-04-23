package app.exam.service.imp;

import app.exam.domain.dto.json.ItemJSONImportDTO;
import app.exam.domain.entities.Category;
import app.exam.domain.entities.Item;
import app.exam.parser.ValidationUtil;
import app.exam.parser.interfaces.ModelParser;
import app.exam.repository.CategoryRepository;
import app.exam.repository.ItemsRepository;
import app.exam.service.api.ItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ItemsServiceImp implements ItemsService {

    private final CategoryRepository categoryRepository;
    private final ItemsRepository itemsRepository;
    private final ModelParser mapper;

    @Autowired
    public ItemsServiceImp(CategoryRepository categoryRepository, ItemsRepository itemsRepository, ModelParser mapper) {
        this.categoryRepository = categoryRepository;
        this.itemsRepository = itemsRepository;
        this.mapper = mapper;
    }

    @Override
    public void create(ItemJSONImportDTO itemJSONImportDTO) {
        if (! ValidationUtil.isValid(itemJSONImportDTO)) {
            throw new IllegalArgumentException();
        }
        Item item = this.mapper.convert(itemJSONImportDTO, Item.class);
        Category category = this.categoryRepository.findByName(itemJSONImportDTO.getCategoryName());
        if (category == null) {
            category = new Category();
            category.setName(itemJSONImportDTO.getCategoryName());
        }
        List<String> namesOfProducts = this.itemsRepository.findAll()
                .stream()
                .map(Item::getName)
                .collect(Collectors.toList());
        if (namesOfProducts.contains(item.getName())) {
            throw new IllegalArgumentException();
        }
        this.categoryRepository.save(category);
        item.setCategory(category);
        this.itemsRepository.save(item);
    }
}
