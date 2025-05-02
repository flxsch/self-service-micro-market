package com.micromarket.core_service.domain.order;

import com.micromarket.core_service.domain.order.dto.OrderDTO;
import com.micromarket.core_service.domain.order.dto.OrderMapper;
import com.micromarket.core_service.domain.product.Product;
import com.micromarket.core_service.domain.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(final OrderService orderService, final OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<OrderDTO>> getAll() {
        List<Order> orders = orderService.findAll();
        return new ResponseEntity<>(orderMapper.toDTOs(orders), HttpStatus.OK);
    }

    @GetMapping({"/{id}", "/{id}/"})
    public ResponseEntity<OrderDTO> getById(@PathVariable final String id) {
        Order order = orderService.findById(id);
        return new ResponseEntity<>(orderMapper.toDTO(order), HttpStatus.OK);
    }

    @GetMapping({"/payment/{id}", "/payment/{id}/"})
    public ResponseEntity<OrderDTO> getByPaymentId(@PathVariable String id) {
        Order order = orderService.findByPaymentId(id);
        return new ResponseEntity<>(orderMapper.toDTO(order), HttpStatus.OK);
    }

    @PostMapping({"", "/" })
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO order){
        Order created = orderService.save(orderMapper.fromDTO(order));
        return new ResponseEntity<>(orderMapper.toDTO(created), HttpStatus.CREATED);
    }

    @GetMapping({"/{id}/products", "/{id}/ /"})
    public ResponseEntity<List<ProductDTO>> getProductsByOrderId(@PathVariable final String id) {
        List<Product> products = orderService.findProductsByOrderId(id);
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> new ProductDTO(
                        product.getName(),
                        product.getCategory(),
                        product.getStock(),
                        product.getPrice(),
                        product.getBarcode()
                ))
                .toList();
        return new ResponseEntity<>(productDTOs, HttpStatus.OK);
    }


}
