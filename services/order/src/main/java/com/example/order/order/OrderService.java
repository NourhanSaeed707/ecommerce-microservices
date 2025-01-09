package com.example.order.order;
import com.example.order.customer.CustomerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;

    public Integer createOrder(OrderRequest orderRequest) {
      // check customer --> openFeign

    }
}
