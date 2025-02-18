package com.example.order.order;
import com.example.order.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    Integer id;
    String reference;
    @Positive(message = "Order amount should be positive")
    BigDecimal amount;
    @NotNull(message = "Payment method should be precised")
    PaymentMethod paymentMethod;
    @NotNull(message = "Customer should be present")
    @NotEmpty(message = "Customer should be present")
    @NotBlank(message = "Customer should be present")
    String customerId;
    @NotEmpty(message = "You should at least purchase one product")
    List<PurchaseRequest> products;
}
