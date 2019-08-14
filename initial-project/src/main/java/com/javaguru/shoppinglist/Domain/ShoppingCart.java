package com.javaguru.shoppinglist.Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "cart")
public class ShoppingCart {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cartId", nullable = false)
    private long id;

    public long getCartId() {
        return id;
    }
    public void setCartId(long cartId) {
        id= cartId;
    }

    @Column(name = "name", nullable = false)
    private String cartName;

    public String getCartName() { return this.cartName; }
    public void setCartName(String name) {
        this.cartName = name;
    }

    @ManyToMany
    private List<Product> products = new ArrayList<>();

    public List<Product> getList() { return products; }
    public void setList(List<Product> products) { this.products = products; }

    @Override
    public int hashCode() {
        return Objects.hash(id, cartName);
    }
}
