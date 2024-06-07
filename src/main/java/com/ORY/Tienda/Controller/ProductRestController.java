package com.ORY.Tienda.Controller;

import com.ORY.Tienda.Domain.Product;
import com.ORY.Tienda.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/Products")
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProductos() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductoById(@PathVariable Integer id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return new ResponseEntity<>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Product> createProducto(@RequestBody Product product) {
        Product savedProduct = productService.saveProduct(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProducto(@PathVariable Integer id, @RequestBody Product product) {
        Optional<Product> existingProduct = productService.findById(id);
        if (existingProduct.isPresent()) {
            product.setId(id);
            Product updatedProduct = productService.saveProduct(product);
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        Optional<Product> existingProduct = productService.findById(id);
        if (existingProduct.isPresent()) {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

/*
    @PostMapping
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        if(productServiceIMPL.addProduct(product)){
            URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(product.getId())
                    .toUri();
            return ResponseEntity.created(location).body(product);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<?> editProduct(@RequestBody Product product){
        Product editedproduct = productServiceIMPL.changeProduct(product);
        if (editedproduct != null){
            return ResponseEntity.ok(editedproduct);
        }
        log.warn("The product has been no found");
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        Optional prduct = productServiceIMPL.deleteProduct(id);
        if(prduct.isPresent()){
            return ResponseEntity.ok(prduct.get());
        }
        log.error("Product {} has been no found", id);
        return ResponseEntity.notFound().build();
    }

*/

