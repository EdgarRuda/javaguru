package com.javaguru.shoppinglist.Service.Validation.ProductValidation;

import com.javaguru.shoppinglist.Domain.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DiscountValidation implements ProductValidationRule {
    public DiscountValidation() {
    }

    public void validate(Product product) throws ProductValidationException {
        if (product.getDiscount().compareTo(new BigDecimal(100)) >= 0) {
            throw new ProductValidationException("Discount cannot be more than 100%");
        } else if (product.getDiscount().compareTo(new BigDecimal(0)) < 0) {
            throw new ProductValidationException("Discount cannot be less than 0");
        }
    }
}
