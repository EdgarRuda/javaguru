package com.javaguru.shoppinglist.Service.Validation.ProductValidation;

import com.javaguru.shoppinglist.Domain.Product;
import org.springframework.stereotype.Component;

@Component
public class NameValidation implements ProductValidationRule {
    public NameValidation(){}
    public void validate(Product product) throws ProductValidationException {
        if (product.getName().length() < 3 || product.getName().length() > 32)
            throw new ProductValidationException("Name cannot be shorter than 3 chars or longer than 32");
    }
}
