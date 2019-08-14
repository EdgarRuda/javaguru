package com.javaguru.shoppinglist.Service;

import com.javaguru.shoppinglist.Domain.Product;
import com.javaguru.shoppinglist.Repository.ProductRepository;
import com.javaguru.shoppinglist.Service.Validation.ProductValidation.ProductValidationException;
import com.javaguru.shoppinglist.Service.Validation.ProductValidation.ProductValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductValidationService validationService;
@Autowired
    public ProductService(ProductRepository repository, ProductValidationService validationService){
        this.repository=repository;
        this.validationService = validationService;
    }

    public Product create(String name, BigDecimal price, BigDecimal discount) throws ProductValidationException {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        product.setDiscount(discount);
        validationService.validate(product);
        repository.add(product);
        System.out.print("Product id: " + product.getId() + "\n");
        return product;

    }

    public Product findById(Long id){
        Product product = repository.findProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product by id: " + id + " wasn't found"));
        System.out.println(product.toString());
        return product;
    }
}
