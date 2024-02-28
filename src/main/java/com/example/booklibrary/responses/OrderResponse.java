package com.example.booklibrary.responses;

import com.example.booklibrary.entities.Order;
import lombok.Data;

@Data
public class OrderResponse {
    private Long id;
    private BookResponse book;
    private String phone;
    private Boolean active;
    private Integer cost;

    public OrderResponse(Order order) {
        setId(order.getId());
        setBook(new BookResponse(order.getBook()));
        setPhone(order.getPhone());
        setActive(order.getActive());
        setCost(order.getBook().getCost());
    }
}
