package com.javaguru.shoppinglist.dto;

import com.javaguru.shoppinglist.Domain.Product;

import java.util.ArrayList;
import java.util.List;

public class CartDTO {

    public CartDTO(long id, String name){
        this.id = id;
        cartName = name;
    }
    private long id;

    public long getCartId() {
        return id;
    }

    public void setCartId(long cartId) {
        id = cartId;
    }

    private String cartName;

    public String getCartName() {
        return this.cartName;
    }

    public void setCartName(String name) {
        this.cartName = name;
    }

    private List<Product> products = new ArrayList<>();

    public List<Product> getList() {
        return products;
    }

    public void setList(List<Product> products) {
        this.products = products;
    }

}
