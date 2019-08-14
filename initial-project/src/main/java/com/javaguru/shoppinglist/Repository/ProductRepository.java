package com.javaguru.shoppinglist.Repository;

import com.javaguru.shoppinglist.Domain.Product;

import java.util.Optional;

public interface ProductRepository {

     Product add(Product product);
     void update(Product product);
     Optional<Product> findProductById(Long id);
     boolean existByName(String name);
}
