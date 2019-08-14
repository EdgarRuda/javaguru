package com.javaguru.shoppinglist.Service.Validation.ProductValidation;

import com.javaguru.shoppinglist.Domain.Product;

public interface ProductValidationRule {

    void validate(Product product) throws ProductValidationException;

}
