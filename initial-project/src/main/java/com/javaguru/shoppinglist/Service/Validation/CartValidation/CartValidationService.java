package com.javaguru.shoppinglist.Service.Validation.CartValidation;

import com.javaguru.shoppinglist.Repository.CartRepository;
import org.springframework.stereotype.Component;

@Component
public class CartValidationService {

    private final CartRepository repository;

    public CartValidationService(CartRepository repository){
        this.repository = repository;
    }

    public void CheckNotEmpty(Long id) throws CartValidationException {
        new CheckNotEmpty(repository).validate(id);
    }

    public void CheckDuplicate(String name) throws CartValidationException{
        new CheckDuplicate(repository).validate(name);
    }
}
