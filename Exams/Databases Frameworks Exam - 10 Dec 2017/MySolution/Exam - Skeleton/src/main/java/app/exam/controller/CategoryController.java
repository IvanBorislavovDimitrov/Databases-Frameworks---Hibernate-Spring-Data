package app.exam.controller;

import app.exam.parser.XMLParser;
import app.exam.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.util.List;

@Controller
public class CategoryController {

    private final CategoryService categoryService;
    private final XMLParser xmlParser;

    @Autowired
    public CategoryController(CategoryService categoryRepository, XMLParser xmlParser) {
        this.categoryService = categoryRepository;
        this.xmlParser = xmlParser;
    }

    public String getCategoriesWithMostPopularItemsSorted(List<String> categoryNames) {
        try {
            return this.xmlParser.write(this.categoryService.getCategoriesWithMostPopularItems(categoryNames));
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return null;
    }
}
