package com.javaguru.shoppinglist.Repository;

import com.javaguru.shoppinglist.Domain.Product;
import com.javaguru.shoppinglist.Domain.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface CartRepository {

     void add(ShoppingCart cart) ;

     void update(ShoppingCart shoppingCart);

     void deleteCart(Long cartId);

     Optional<ShoppingCart> findCartById(Long id);
     boolean existByName(String name);

     List<Product> getList(Long id);
}
