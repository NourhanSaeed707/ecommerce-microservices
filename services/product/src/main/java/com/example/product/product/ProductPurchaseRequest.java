package com.example.product.product;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductPurchaseRequest {
    @NotNull(message = "Product is mandatory")
    private Integer productId;
    @NotNull(message = "Quantity is mandatory")
    private double quantity;
}
