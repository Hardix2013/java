package ru.mylife54.services;


import ru.mylife54.models.Product;

import java.util.List;

public interface ProductService {
    void saveProduct(Product product);

    void deleteProduct(Product product);

    Product getProduct(String name);

    List<Product> getAll();
}
