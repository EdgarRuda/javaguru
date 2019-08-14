package com.javaguru.shoppinglist.Service.Validation.ProductValidation;

import com.javaguru.shoppinglist.Domain.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PriceValidation implements ProductValidationRule {
     public PriceValidation() {
    }

    public void validate(Product product) throws ProductValidationException {
        if (product.getPrice().compareTo(new BigDecimal(0)) < 1) {
            throw new ProductValidationException("Price cannot be lower than 0");
        }
    }
}
