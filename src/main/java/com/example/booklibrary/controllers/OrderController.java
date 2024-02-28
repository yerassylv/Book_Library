package com.example.booklibrary.controllers;

import com.example.booklibrary.requests.OrderDeletionRequest;
import com.example.booklibrary.requests.OrderRequest;
import com.example.booklibrary.responses.ErrorMessageResponse;
import com.example.booklibrary.responses.OrderResponse;
import com.example.booklibrary.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/all")
    public List<OrderResponse> getAllOrders(
            @RequestParam(required = false) String phoneNumber,
            @RequestParam(required = false) Long bookId
    ) {
        if (phoneNumber != null)
            return orderService.getOrdersByPhoneNumber(phoneNumber);

        if (bookId != null)
            return orderService.getOrdersByBook(bookId);

        return orderService.getAllOrders();
    }

    @PostMapping("/create")
    public OrderResponse createOrder(
            @RequestBody OrderRequest request
    ) {
        return orderService.createOrder(request);
    }

    @DeleteMapping("/delete")
    public void deleteOrder(
            @RequestBody OrderDeletionRequest request
    ) {
        orderService.deleteOrder(request);
    }
}
