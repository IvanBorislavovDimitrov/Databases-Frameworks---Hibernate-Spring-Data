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
public class ItemServiceImp implements ItemsService {

    private final ItemsRepository itemsRepository;
    private final CategoryRepository categoryRepository;
    private final ModelParser modelParser;

    @Autowired
    public ItemServiceImp(ItemsRepository itemsRepository, CategoryRepository categoryRepository, ModelParser modelParser) {
        this.itemsRepository = itemsRepository;
        this.categoryRepository = categoryRepository;
        this.modelParser = modelParser;
    }

    @Override
    public void create(ItemJSONImportDTO itemJSONImportDTO) {
        if (! ValidationUtil.validate(itemJSONImportDTO)) {
            throw new IllegalArgumentException();
        }

        Category category = this.categoryRepository.findByName(itemJSONImportDTO.getCategory());
        if (category == null) {
            category = new Category();
            category.setName(itemJSONImportDTO.getCategory());
            this.categoryRepository.save(category);
        }

        Item item = new Item();
        item.setPrice(itemJSONImportDTO.getPrice());
        item.setName(itemJSONImportDTO.getName());
        item.setCategory(category);

        List<String> allItems = this.itemsRepository.findAll().stream().map(x -> x.getName()).collect(Collectors.toList());
        if (allItems.contains(item.getName())) {
            throw new IllegalArgumentException();
        }

        this.itemsRepository.saveAndFlush(item);
    }
}
