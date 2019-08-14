package com.javaguru.shoppinglist.Service.Validation.ProductValidation;

import com.javaguru.shoppinglist.Domain.Product;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ProductValidationService {
    private final Set<ProductValidationRule> validationRules;

    public  ProductValidationService(Set<ProductValidationRule> Rules) {
        this.validationRules= Rules;
    }

    public void validate(Product product) throws ProductValidationException {
        for(ProductValidationRule ob : validationRules)
            ob.validate(product);
    }


}
