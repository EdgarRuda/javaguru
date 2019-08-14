package com.javaguru.shoppinglist.dto;

import java.math.BigDecimal;

public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal discount;

    public ProductDTO(Long id, String name, BigDecimal price, BigDecimal discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }


}
