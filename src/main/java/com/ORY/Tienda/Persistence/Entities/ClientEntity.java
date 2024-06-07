package com.ORY.Tienda.Persistence.Entities;

import com.googlecode.jmapper.annotations.JMap;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="clients")
@Data
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JMap
    private Integer id;
    @JMap
    private String user_name;
    private String password;
    private String email;
}