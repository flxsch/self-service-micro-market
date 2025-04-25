package com.micromarket.core_service.domain.product;

import com.micromarket.core_service.domain.product.dto.ProductDTO;
import com.micromarket.core_service.domain.product.dto.ProductMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Collection<ProductDTO>> getAllProducts() {
        try {
        return new ResponseEntity<>(
                productMapper.toDTOs(productService.findAll()),
                HttpStatus.OK
        );
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<ProductDTO> getProductById(@PathVariable String id) {
        try {
            Product product = productService.findById(id);
            return product != null
                    ? new ResponseEntity<>(productMapper.toDTO(product), HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // TODO global exception handling to display error message
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping({"", "/"})
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO product) {
        try {
            Product created = productService.save(productMapper.fromDTO(product));
            return new ResponseEntity<>(productMapper.toDTO(created), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody ProductDTO product) {
        Product entity = productMapper.fromDTO(product);

        try {
            var updated = productService.updateById(id, entity);
            return updated != null
                    ? new ResponseEntity<>(updated, HttpStatus.OK)
                    : new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            // TODO global exception handling to display error message
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping({"/{id}", "/{id}/"})
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        try {
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch(Exception e) {
            // TODO global exception handling to display error message
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
