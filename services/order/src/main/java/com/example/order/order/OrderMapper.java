package com.example.order.order;
import org.springframework.stereotype.Service;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest orderRequest) {
        return Order.builder()
                .id(orderRequest.getId())
                .customerId(orderRequest.getCustomerId())
                .reference(orderRequest.getReference())
                .totalAmount(orderRequest.getAmount())
                .paymentMethod(orderRequest.getPaymentMethod())
                .build();
    }
}
