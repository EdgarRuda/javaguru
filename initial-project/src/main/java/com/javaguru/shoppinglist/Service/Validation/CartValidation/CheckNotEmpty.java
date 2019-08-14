package com.javaguru.shoppinglist.Service.Validation.CartValidation;


import com.javaguru.shoppinglist.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckNotEmpty {
    private final CartRepository repository;
@Autowired
    public CheckNotEmpty(CartRepository repository){
        this.repository = repository;
    }

    public void validate(Long id) throws CartValidationException{
        if( repository.getList(id).isEmpty())
            throw new CartValidationException("Cart is empty");
    }
}
