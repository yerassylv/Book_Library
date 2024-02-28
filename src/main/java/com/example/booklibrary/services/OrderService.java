package com.example.booklibrary.services;

import com.example.booklibrary.entities.Book;
import com.example.booklibrary.entities.Order;
import com.example.booklibrary.repositories.BookRepository;
import com.example.booklibrary.repositories.OrderRepository;
import com.example.booklibrary.requests.OrderDeletionRequest;
import com.example.booklibrary.requests.OrderRequest;
import com.example.booklibrary.responses.OrderResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    public List<OrderResponse> getAllOrders() {
        return orderRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .stream()
                .map(OrderResponse::new)
                .toList();
    }

    public OrderResponse getOrderById(Long id) {
        return new OrderResponse(orderRepository.findById(id).orElseThrow(EntityNotFoundException::new));
    }

    public List<OrderResponse> getOrdersByPhoneNumber(String phone) {
        return orderRepository.findOrdersByPhone(phone)
                .stream()
                .map(OrderResponse::new)
                .toList();
    }

    @Transactional
    public List<OrderResponse> getOrdersByBook(Long id) {
        return bookRepository.findById(id).orElseThrow(EntityNotFoundException::new).getOrders()
                .stream()
                .map(OrderResponse::new)
                .toList();
    }

    public OrderResponse createOrder(OrderRequest request) {
        Book book = bookRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
        if (book.getAvailability() < 1)
            throw new EntityNotFoundException();


        book.setAvailability(book.getAvailability() - 1);
        book = bookRepository.save(book);

        Order order = Order.builder()
                .book(book)
                .phone(request.getPhone())
                .active(true)
                .build();
        return new OrderResponse(orderRepository.save(order));
    }

    public void deleteOrder(OrderDeletionRequest request) {
        Order order = orderRepository.findById(request.getId()).orElseThrow(EntityNotFoundException::new);
        Book book = order.getBook();
        book.setAvailability(book.getAvailability() + 1);
        bookRepository.save(book);
        orderRepository.delete(order);
    }
}
