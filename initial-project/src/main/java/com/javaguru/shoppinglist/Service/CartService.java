
package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Domain.Product;
import com.javaguru.shoppinglist.Domain.ShoppingCart;
import com.javaguru.shoppinglist.Repository.CartRepository;
import com.javaguru.shoppinglist.Service.Validation.CartValidation.CartValidationException;
import com.javaguru.shoppinglist.Service.Validation.CartValidation.CartValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service

public class CartService {
    private final CartRepository repository;
    private final ProductService productService;
    private final CartValidationService cartValidation;
@Autowired
    public CartService(CartRepository repository, ProductService productService, CartValidationService cartValidation) {
        this.repository = repository;
        this.productService = productService;
        this.cartValidation = cartValidation;
    }

    public void create(String cartName) throws CartValidationException {
        cartValidation.CheckDuplicate(cartName);
        ShoppingCart cart = new ShoppingCart();
        cart.setCartName(cartName);
        repository.add(cart);
        System.out.println("Cart id: " + cart.getCartId());
    }

   public ShoppingCart findCartById(Long id){
       return repository.findCartById(id)
               .orElseThrow(() -> new NoSuchElementException("Cart by id " + id + " wasn't found"));
   }

   @Transactional
    public void addProduct(Product product, Long id) {
       ShoppingCart cart=  repository.findCartById(id)
               .orElseThrow(() -> new IllegalArgumentException("Cart by id: " + id + " wasn't found"));

        cart.getList().add(product);
        repository.update(cart);

    }
    @Transactional
    public void deleteCart( Long cartId) {

        repository.deleteCart(cartId);
    }

    @Transactional
    public void removeProduct(Long productId, Long cartId){
        ShoppingCart cart = repository.findCartById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Cart by id: " + cartId + " wasn't found"));

        cart.getList().remove(productService.findById(productId));
        repository.update(cart);
    }
@Transactional
    public List<Product> showInfo(Long id)  throws CartValidationException {
        cartValidation.CheckNotEmpty(id);
        ShoppingCart cart = repository.findCartById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cart by id: " + id + " wasn't found"));

    return repository.getList(cart.getCartId());
    }

    public BigDecimal showTotalPrice(Long cartId) throws CartValidationException {
        cartValidation.CheckNotEmpty(cartId);
        BigDecimal total = new BigDecimal(0);
        for(Product ob : repository.getList(cartId) )
            total = total.add(ob.getPrice());
        System.out.print("Total price : " + total + "\n");
        return total;
    }
}
