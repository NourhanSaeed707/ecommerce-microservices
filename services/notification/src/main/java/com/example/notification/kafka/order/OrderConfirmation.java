package com.example.notification.kafka.order;
import com.example.notification.kafka.payment.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderConfirmation {
    private String orderReference;
    private BigDecimal totalAmount;
    private PaymentMethod paymentMethod;
    private Customer customer;
    List<Product> products;
}
