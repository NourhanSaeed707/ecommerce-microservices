package com.example.order.product;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class PurchaseRequest {
    @NotNull(message = "Product is mandatory")
    private Integer productId;
    @Positive(message = "Quantity is nadatory")
    private double quantity;
}
