package app.model.repositories;

import app.model.enums.Size;
import app.model.shampoos.BasicShampoo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


@Repository
public interface BasicShampooRepo extends CrudRepository<BasicShampoo, Long> {

    @Query(value = "select b from BasicShampoo b where b.size = :sizeS")
    List<BasicShampoo> getShampoosWithSize(@Param("sizeS")Size size);

    @Query(value = "select b from BasicShampoo b where b.size = :sizeP or b.label.id = :labelP")
    List<BasicShampoo> getShampoosBySizeOrLabel(@Param("sizeP") Size size, @Param("labelP") long labelId);

    @Query(value = "select b from BasicShampoo b where b.price > :price order by b.price desc")
    List<BasicShampoo> getAllByPriceHigherThan(@Param("price") BigDecimal price);

    int countAllByPriceLessThan(BigDecimal price);

    @Query(value = "select b from BasicShampoo b join b.ingredients i where i.name in :ingredientSet")
    List<BasicShampoo> getShampoosByIngredients(@Param("ingredientSet") Set<String> ingredients);

    @Query(value = "select b from BasicShampoo b join b.ingredients i group by b.id having b.ingredients.size < :countOfIngredients")
    List<BasicShampoo> getShampoosByIngredientsCount(@Param("countOfIngredients") int count);

    @Query(value = "select sum(i.price) from BasicShampoo b join b.ingredients i where b.brand = :brand")
    BigDecimal sumOfIngredients(@Param("brand") String brand);

}
