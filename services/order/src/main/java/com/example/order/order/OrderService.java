package com.example.order.order;
import com.example.order.customer.CustomerClient;
import com.example.order.exceptions.BusinessException;
import com.example.order.kafka.OrderConfirmation;
import com.example.order.kafka.OrderProducer;
import com.example.order.orderLine.OrderLineRequest;
import com.example.order.orderLine.OrderLineService;
import com.example.order.payment.PaymentClient;
import com.example.order.payment.PaymentRequest;
import com.example.order.product.ProductClient;
import com.example.order.product.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderMapper mapper;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest orderRequest) {
        // check customer --> openFeign
        var customer = this.customerClient.findCustomerById(orderRequest.getCustomerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with provided id: " + orderRequest.getCustomerId()));
        // purchase product --> RestTemplate
        var purchasedProducts = this.productClient.purchaseProducts(orderRequest.getProducts());
        var order = this.orderRepository.save(mapper.toOrder(orderRequest));
        // persist order lines
        for (PurchaseRequest purchaseRequest : orderRequest.getProducts()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.getProductId(),
                            purchaseRequest.getQuantity()
                    )
            );
        }
        var paymentRequest = new PaymentRequest(
                orderRequest.getAmount(),
                orderRequest.getPaymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        orderRequest.getReference(),
                        orderRequest.getAmount(),
                        orderRequest.getPaymentMethod(),
                        customer,
                        purchasedProducts
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll().stream().map(mapper::fromOrder).collect(Collectors.toList());
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId).map(mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("No order found with the provided id: " + orderId));
    }
}
