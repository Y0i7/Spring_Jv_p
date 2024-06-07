package com.ORY.Tienda.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private Integer id;
    private String user_name;
    private String password;
    private String email;
}
