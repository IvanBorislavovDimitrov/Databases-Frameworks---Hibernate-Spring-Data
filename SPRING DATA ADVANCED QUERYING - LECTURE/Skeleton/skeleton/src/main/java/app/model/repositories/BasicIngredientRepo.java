package app.model.repositories;

import app.model.ingredients.BasicIngredient;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public interface BasicIngredientRepo extends CrudRepository<BasicIngredient, Long> {

    List<BasicIngredient> findAllByNameStartingWith(String pref);

    @Query("select b from BasicIngredient b where b.name in :setOfIngredients")
    List<BasicIngredient> findAllWhichAreContainedInList(@Param("setOfIngredients") Set<String> ingredients);

    @Transactional
    @Modifying
    @Query(value = "delete from BasicIngredient b where b.name = :name")
    void deleteAllByName(@Param("name") String name);

    @Transactional
    @Modifying
    @Query(value = "update BasicIngredient b set b.price = b.price * 1.10")
    void updatePricesOfIngredients();

    @Transactional
    @Modifying
    @Query(value = "update BasicIngredient b set b.price = b.price * 1.10 where b.name in :names")
    void updatePriceByGivenNames(@Param("names") Set<String> names);
}
