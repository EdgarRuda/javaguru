package com.javaguru.shoppinglist.Console;

import com.javaguru.shoppinglist.Domain.Product;
import com.javaguru.shoppinglist.Service.CartService;
import com.javaguru.shoppinglist.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Scanner;

@Component
public class ConsoleUI {
    private final ProductService productService;
    private CartService cartService;
    private Scanner scanner = new Scanner(System.in);
    @Autowired
    public ConsoleUI(ProductService productService, CartService cartService){

        this.productService = productService;
        this.cartService = cartService;
    }

    public void execute() {
        start:
        while(true) {

            try {
                System.out.println("1. Database");
                System.out.println("2. Shopping cart");
                System.out.println("3. Exit");
                switch(scanner.nextInt()) {
                    case 1:
                            while(true) {
                                System.out.println("1. Create product");
                                System.out.println("2. Find by id");
                                System.out.println("3. Go back");
                                switch(scanner.nextInt()) {
                                    case 1:
                                        scanner.nextLine();
                                        System.out.println("Enter product name: ");
                                        String name = scanner.nextLine();
                                        System.out.println("Enter Product price: ");
                                        BigDecimal price = new BigDecimal(scanner.nextInt());
                                        BigDecimal discount;
                                        if (price.compareTo(new BigDecimal(20)) >= 1) {
                                            System.out.println("Enter discount: ");
                                            discount = new BigDecimal(scanner.nextInt());
                                        } else
                                            discount = new BigDecimal(0);
                                        productService.create(name, price, discount);

                                        break;
                                    case 2:
                                        System.out.println("Enter Id: ");
                                        productService.findById(scanner.nextLong());
                                        break;
                                    case 3:
                                        continue start;
                            }
                }

                    case 2:
                        while(true) {
                            cartMenu:
                            while(true) {

                                System.out.println("1. Create shopping cart");
                                System.out.println("2. Open shopping cart");
                                System.out.println("3. Delete shopping cart");
                                System.out.println("4. Go back");
                                switch(scanner.nextInt()) {
                                    case 1:
                                        scanner.nextLine();
                                        System.out.println("Enter cart name: ");
                                        String cartName = scanner.nextLine();
                                        cartService.create(cartName);
                                        System.out.println();
                                        continue cartMenu;
                                    case 2:
                                        System.out.println("Enter cart Id: ");
                                        Long cartId = cartService.findCartById(scanner.nextLong()).getCartId();
                                        System.out.println(cartId);
                                            while(true) {
                                                System.out.println("1. Add product");
                                                System.out.println("2. Remove product");
                                                System.out.println("3. Show info");
                                                System.out.println("4. Show total price");
                                                System.out.println("5. Go back");
                                                switch(scanner.nextInt()) {
                                                    case 1:
                                                        scanner.nextLine();
                                                        System.out.println("Enter Product Id:");
                                                        Product product = productService.findById(scanner.nextLong());
                                                        cartService.addProduct(product, cartId);
                                                        break;
                                                    case 2:
                                                        System.out.println("Enter Product Id:");
                                                        cartService.removeProduct( scanner.nextLong(), cartId);
                                                        break;
                                                    case 3:
                                                        cartService.showInfo(cartId);
                                                        break;
                                                    case 4:
                                                        cartService.showTotalPrice(cartId);
                                                        break;
                                                    case 5:
                                                        continue cartMenu;
                                                }
                                        }
                                    case 3:
                                        scanner.nextLine();
                                        System.out.println("Enter cart Id: ");
                                        cartService.deleteCart(scanner.nextLong());
                                        break;
                                    case 4:
                                        continue start;
                                }
                            }
                        }
                    case 3:
                        return;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
