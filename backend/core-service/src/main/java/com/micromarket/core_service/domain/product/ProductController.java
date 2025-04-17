package com.micromarket.core_service.domain.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<String> getHelloWorld() {
        return new ResponseEntity<String>("Hello product", HttpStatus.OK);
    }
}
