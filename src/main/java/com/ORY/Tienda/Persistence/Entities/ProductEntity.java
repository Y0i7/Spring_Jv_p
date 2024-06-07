package com.ORY.Tienda.Persistence.Entities;

import com.googlecode.jmapper.annotations.JMap;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "products")
@Data
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JMap
    private Integer id;
    @JMap
    private String name;
    private Double price;
    private Integer stock;
}

