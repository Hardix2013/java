package ru.mylife54.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.mylife54.models.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    Product findByName(String name);

}
