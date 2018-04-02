package app.model.service;

import app.model.ingredients.BasicIngredient;
import app.model.repositories.BasicIngredientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class IngredientService {

    private BasicIngredientRepo basicIngredientRepo;

    @Autowired
    public IngredientService(BasicIngredientRepo basicIngredientRepo) {
        this.basicIngredientRepo = basicIngredientRepo;
    }

    public List<BasicIngredient> findAllByNameStartingWith(String pref) {
        return this.basicIngredientRepo.findAllByNameStartingWith(pref);
    }

    public List<BasicIngredient> findAllWhichAreContainedInList(Set<String> ingredients) {
        return this.basicIngredientRepo.findAllWhichAreContainedInList(ingredients);
    }


    public void deleteAllByName(@Param("name") String name) {
        this.basicIngredientRepo.deleteAllByName(name);
    }

    public void updatePricesOfIngredients() {
        this.basicIngredientRepo.updatePricesOfIngredients();
    }

    public void updatePriceByGivenNames(@Param("names") Set<String> names) {
        this.basicIngredientRepo.updatePriceByGivenNames(names);
    }

}
