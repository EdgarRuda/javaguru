package com.javaguru.shoppinglist.Controller;

import com.javaguru.shoppinglist.Domain.Product;
import com.javaguru.shoppinglist.Service.ProductService;
import com.javaguru.shoppinglist.Service.Validation.ProductValidation.ProductValidationException;
import com.javaguru.shoppinglist.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService){this.productService = productService;}

    @PostMapping("/product")
    public Product create (@RequestBody Product productDTO) throws ProductValidationException {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setDiscount(productDTO.getDiscount());
        return productService.create(productDTO.getName(),product.getPrice(),productDTO.getDiscount());
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<ProductDTO> findProductById(@PathVariable("productId") Long id){
        Product product = productService.findById(id);
        return  ResponseEntity.ok().body( new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getDiscount()));
    }
}
