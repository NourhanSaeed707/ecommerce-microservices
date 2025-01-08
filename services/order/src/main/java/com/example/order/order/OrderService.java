package com.example.order.order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Integer createOrder(OrderRequest orderRequest) {
        
    }
}
