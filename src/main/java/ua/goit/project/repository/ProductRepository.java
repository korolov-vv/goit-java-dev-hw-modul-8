package ua.goit.project.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ua.goit.project.model.entity.Product;

import java.util.Optional;

public interface ProductRepository extends Repository<Product> {
    @Override
    @Query("select p from Product p where p.productName=?1")
    Optional<Product> findByUniqueValue(String value);

    @Override
    @Modifying
    @Query("delete from Product p where p.productName=?1")
    void deleteByUniqueValue(String value);
}