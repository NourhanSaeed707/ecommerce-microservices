package com.example.product.product;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public Integer createProduct(ProductRequest productRequest) {
        var product = mapper.toProduct(productRequest);
        return productRepository.save(product).getId();
    }

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequestList) {
       var productIds = productPurchaseRequestList.stream().map(ProductPurchaseRequest::getProductId).toList();
       var storedProducts = productRepository.findByIdInOrderById(productIds);
    }

    public ProductResponse findById(Integer productId) {
        productRepository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }
}
