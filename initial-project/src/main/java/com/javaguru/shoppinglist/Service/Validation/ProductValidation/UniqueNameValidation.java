package com.javaguru.shoppinglist.Service.Validation.ProductValidation;

import com.javaguru.shoppinglist.Domain.Product;

import com.javaguru.shoppinglist.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqueNameValidation implements ProductValidationRule {

    private final ProductRepository repository;
@Autowired
    public UniqueNameValidation(ProductRepository repository){
        this.repository = repository;
    }
    public void validate(Product product) throws ProductValidationException {
        if (!repository.existByName(product.getName()))
            throw new ProductValidationException("Product with this name already exists");
    }
}
