package com.javaguru.shoppinglist.Service.Validation.CartValidation;

import com.javaguru.shoppinglist.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckDuplicate {

    private final CartRepository repository;
@Autowired
    public CheckDuplicate(CartRepository repository){
        this.repository = repository;
    }
    public void validate(String name) throws CartValidationException{
            if(!repository.existByName(name))
            throw new CartValidationException("Cart already exist");
    }
}
