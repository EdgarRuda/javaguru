package com.javaguru.shoppinglist.Controller;

import com.javaguru.shoppinglist.Domain.Product;
import com.javaguru.shoppinglist.Domain.ShoppingCart;
import com.javaguru.shoppinglist.Service.CartService;
import com.javaguru.shoppinglist.Service.ProductService;
import com.javaguru.shoppinglist.Service.Validation.CartValidation.CartValidationException;
import com.javaguru.shoppinglist.dto.CartDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
public class CartController {

    private CartService cartService;
    private ProductService productService;

    public CartController(CartService cartService, ProductService productService){
        this.cartService = cartService;
        this.productService = productService;
    }

    @PostMapping("/cart")
    public void create (@RequestBody ShoppingCart shoppingCart) throws CartValidationException{

          cartService.create(shoppingCart.getCartName());
    }

    @GetMapping("cart/{cartId}")
    public CartDTO findCartById(@PathVariable("cartId") Long id){
        ShoppingCart shoppingCart = cartService.findCartById(id);
        return new CartDTO(shoppingCart.getCartId(),shoppingCart.getCartName());
    }

   @GetMapping("/cart/{cartId}/total")
   public ResponseEntity<BigDecimal> totalPrice(@PathVariable (value = "cartId")  Long cartId) throws CartValidationException{
        return ResponseEntity.ok().body(cartService.showTotalPrice(cartId));

   }

   @PutMapping("/cart/{cartId}/product/{productId}")
    public void addProduct(@PathVariable(value = "cartId") Long cartId,
                                   @PathVariable(value = "productId") Long productId) {
        Product product = productService.findById(productId);
          cartService.addProduct(product, cartId);
   }
    @DeleteMapping("/cart/{cartId}/delete/{productId}")
    public void deleteProduct(@PathVariable(value = "cartId") Long cartId,
                              @PathVariable (value = "productId") Long productId){
        cartService.removeProduct(productId,cartId);
    }
}

/*
   @DeleteMapping("/cart/{cartId}/delete")
    public void deleteCart(@PathVariable(value = "cartId") Long cartId) throws CartValidationException{

   }

    @GetMapping("/cart/{cartId}/products")
    public ResponseEntity<List<Product>> getAllProductsByCartId(@PathVariable(value="cartId") Long cartId) throws CartValidationException {

}*/