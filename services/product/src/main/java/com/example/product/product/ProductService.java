package com.example.product.product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    public Integer createProduct(ProductRequest productRequest) {
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequestList) {
    }

    public ProductResponse findById(Integer productId) {
    }

    public List<ProductResponse> findAll() {
    }
}
