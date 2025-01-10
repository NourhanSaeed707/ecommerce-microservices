package com.example.order.orderLine;
import com.example.order.order.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {

    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.getId())
                .quantity(request.getQuantity())
                .order(
                        Order.builder()
                                .id(request.getOrderId())
                                .build()
                )
                .productId(request.getProductId())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(orderLine.getId(), orderLine.getQuantity());
    }
}
