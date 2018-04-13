package app.repositories;

import app.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Product findFirstById(Long id);

    @Query("SELECT p FROM Product p where p.buyer is null")
    List<Product> getProductByBuyerIsNull();
}
