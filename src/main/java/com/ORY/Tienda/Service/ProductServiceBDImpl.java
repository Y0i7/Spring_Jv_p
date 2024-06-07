package com.ORY.Tienda.Service;

import com.ORY.Tienda.Domain.Product;
import com.ORY.Tienda.Persistence.Entities.ProductEntity;
import com.ORY.Tienda.Persistence.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Service("BD")
@ConditionalOnProperty(
        value = "products.strategy",
        havingValue = "EN_BD")
public class ProductServiceBDImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        List<ProductEntity> entities = productRepository.findAll();
        return entities.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(Integer id) {
        Optional<ProductEntity> entity = productRepository.findById(id);
        return entity.map(this::mapToDto);
    }

    @Override
    public Product saveProduct(Product product) {
        ProductEntity productEntity = mapToEntity(product);
        ProductEntity savedEntity = productRepository.save(productEntity);
        return mapToDto(savedEntity);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }

    private Product mapToDto(ProductEntity entity) {
        Product dto = new Product();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setStock(entity.getStock());
        return dto;
    }

    private ProductEntity mapToEntity(Product dto) {
        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setStock(dto.getStock());
        return entity;
    }
}