package app;

import app.model.enums.Size;
import app.model.ingredients.Ingredient;
import app.model.service.IngredientService;
import app.model.service.ShampooService;
import app.model.shampoos.Shampoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private ShampooService shampooService;
    private IngredientService ingredientService;

    @Autowired
    public ConsoleRunner(ShampooService shampooService, IngredientService ingredientService) {
        this.shampooService = shampooService;
        this.ingredientService = ingredientService;
    }

    @Override
    public void run(String... strings) throws Exception {
//        this.selectShampoosBySize(Size.MEDIUM);

//        this.selectShampoosBySizeOrLabel(Size.MEDIUM, 10L);

//        this.selectShampoosByPrice(BigDecimal.valueOf(5));

//        this.selectIngredientsByName("M");

//        Set<String> ingredients = new HashSet<>();
//        ingredients.add("Lavender");
//        ingredients.add("Herbs");
//        ingredients.add("Apple");
//        this.selectIngredientsByNames(ingredients);

//        this.countShampoosByPrice(BigDecimal.valueOf(8.50));

//        Set<String> ingredients = new HashSet<>();
//        ingredients.add("Berry");
//        ingredients.add("Mineral-Colagen");
//        this.selectShampoosByIngredients(ingredients);

//        this.selectShampoosByIngredientsCount(2);

//        this.selectIngredientNameAndShampooBrandByName("Fresh it up!");

//        this.deleteIngredientsByName("Apple");

//        this.updateIngredientsByPrice();

//        Set<String> names = new HashSet<>();
//        names.add("Nettle");
//        names.add("Aloe Vera");
//        this.upgradeIngredientsByNames(names);
    }

    private void upgradeIngredientsByNames(Set<String> names) {
        this.ingredientService.updatePriceByGivenNames(names);
    }

    private void updateIngredientsByPrice() {
        this.ingredientService.updatePricesOfIngredients();
    }

    private void deleteIngredientsByName(String name) {
        this.ingredientService.deleteAllByName(name);
        System.out.println("Deleted");
    }

    private void selectIngredientNameAndShampooBrandByName(String brand) {
        System.out.println(this.shampooService.sumOfIngredients(brand));
    }

    private void selectShampoosByIngredientsCount(int count) {
        for (Shampoo shampoo : this.shampooService.getShampoosByIngredientsCount(count)) {
            System.out.println(shampoo.getBrand());
        }
    }

    private void selectShampoosByIngredients(Set<String> ingredients) {
        for (Shampoo shampoo : this.shampooService.getShampoosByIngredients(ingredients)) {
            System.out.println(shampoo.getBrand());
        }
    }

    private void countShampoosByPrice(BigDecimal price) {
        System.out.println(this.shampooService.countAllByPriceLessThan(price));
    }

    private void selectIngredientsByNames(Set<String> ingredients) {
        for (Ingredient ingredient : this.ingredientService.findAllWhichAreContainedInList(ingredients)) {
            System.out.println(ingredient.getName());
        }
    }

    private void selectIngredientsByName(String pref) {
        for (Ingredient ingredient : this.ingredientService.findAllByNameStartingWith(pref)) {
            System.out.println(String.format("%s", ingredient.getName()));
        }
    }

    private void selectShampoosByPrice(BigDecimal price) {
        for (Shampoo shampoo : this.shampooService.getAllByPriceHigherThan(price)) {
            System.out.println(String.format("%s %s %.2flv.", shampoo.getBrand(), shampoo.getSize().name(), shampoo.getPrice()));
        }
    }

    private void selectShampoosBySizeOrLabel(Size size, long labelId) {
        for (Shampoo shampoo : this.shampooService.getShampoosBySizeOrLabel(size, labelId)) {
            System.out.println(String.format("%s %s %.2flv.", shampoo.getBrand(), shampoo.getSize().name(), shampoo.getPrice()));
        }
    }


    private void selectShampoosBySize(Size size) {
        for (Shampoo shampoo : this.shampooService.getShampoosWithSize(size)) {
            System.out.println(String.format("%s %s %.2flv.", shampoo.getBrand(), shampoo.getSize().name(), shampoo.getPrice()));
        }
    }
}
