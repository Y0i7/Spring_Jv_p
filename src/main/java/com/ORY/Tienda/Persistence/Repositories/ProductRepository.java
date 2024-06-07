package com.ORY.Tienda.Persistence.Repositories;

import com.ORY.Tienda.Persistence.Entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}


