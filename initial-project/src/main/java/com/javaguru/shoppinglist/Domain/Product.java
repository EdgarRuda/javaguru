package com.javaguru.shoppinglist.Domain;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name ="product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name  = "productId", nullable = false)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "price", nullable = false)
    private BigDecimal price;
    @Column(name = "discount")
    private BigDecimal discount;

    public List<ShoppingCart> getCart() {
        return cart;
    }

    public void setCart(List<ShoppingCart> cart) {
        this.cart = cart;
    }

    @ManyToMany(mappedBy = "products")
    private List<ShoppingCart> cart = new ArrayList<>();


    //private String description;
    //private Enum category;


    public Long getId() {
        return this.id;
    }
    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return this.discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
        BigDecimal base = price;
        BigDecimal percentage = base.multiply(discount).divide(new BigDecimal(100));
        this.price = base.subtract(percentage);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String toString() {
        return "Product{id=" + this.id + ", name='" + this.name + "', price=" + this.price + ", discount=" + this.discount + "}";
    }

    public int hashCode() {
        return Objects.hash(id, name, price, discount);
    }

}
