package com.ORY.Tienda.Service;

import com.ORY.Tienda.Domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();
    Optional<Product> findById(Integer id);
    Product saveProduct(Product product);
    void deleteProduct(Integer id);
}
