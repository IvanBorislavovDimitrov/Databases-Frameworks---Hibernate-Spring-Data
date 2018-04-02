package app.model.service;

import app.model.enums.Size;
import app.model.repositories.BasicShampooRepo;
import app.model.shampoos.BasicShampoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Service
public class ShampooService {

    private BasicShampooRepo basicShampooRepo;

    @Autowired
    public ShampooService(BasicShampooRepo basicShampooRepo) {
        this.basicShampooRepo = basicShampooRepo;
    }

    public List<BasicShampoo> getShampoosWithSize(Size size) {
        return this.basicShampooRepo.getShampoosWithSize(size);
    }

    public List<BasicShampoo> getShampoosBySizeOrLabel(@Param("sizeP") Size size, @Param("labelP") long labelId) {
        return this.basicShampooRepo.getShampoosBySizeOrLabel(size, labelId);
    }

    public List<BasicShampoo> getAllByPriceHigherThan(@Param("price") BigDecimal price) {
        return this.basicShampooRepo.getAllByPriceHigherThan(price);
    }

    public int countAllByPriceLessThan(BigDecimal price) {
        return this.basicShampooRepo.countAllByPriceLessThan(price);
    }

    public List<BasicShampoo> getShampoosByIngredients(@Param("ingredientSet") Set<String> ingredients) {
        return this.basicShampooRepo.getShampoosByIngredients(ingredients);
    }

    public List<BasicShampoo> getShampoosByIngredientsCount(@Param("countOfIngredients") int count) {
        return this.basicShampooRepo.getShampoosByIngredientsCount(count);
    }

    public BigDecimal sumOfIngredients(@Param("brand") String brand) {
        return this.basicShampooRepo.sumOfIngredients(brand);
    }

}
