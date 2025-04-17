package com.micromarket.core_service.domain.order;

import com.micromarket.core_service.domain.order.dto.OrderDTO;
import com.micromarket.core_service.domain.order.dto.OrderMapper;
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
}
