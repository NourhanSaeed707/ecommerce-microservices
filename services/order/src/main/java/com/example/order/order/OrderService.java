package com.example.order.order;
import com.example.order.customer.CustomerClient;
import com.example.order.exceptions.BusinessException;
import com.example.order.product.ProductClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;

    public Integer createOrder(OrderRequest orderRequest) {
        // check customer --> openFeign
        var customer = this.customerClient.findCustomerById(orderRequest.getCustomerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with provided id: " + orderRequest.getCustomerId()));
        // purchase product --> RestTemplate
        this.productClient.purchaseProducts(orderRequest.getProducts());
        var order = this.orderRepository.save(mapper::toOrder(orderRequest));
        // persist order

    }
}
