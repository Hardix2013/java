package ru.mylife54.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mylife54.models.Product;
import ru.mylife54.repo.ProductRepo;
import ru.mylife54.services.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepo productRepo;


    @Override
    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepo.delete(product);
    }

    @Override
    public Product getProduct(String name) {
        return productRepo.findByName(name);
    }

    @Override
    public List<Product> getAll() {
        return productRepo.findAll();
    }
}
