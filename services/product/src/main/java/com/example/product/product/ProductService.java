package com.example.product.product;
import com.example.product.exceptions.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

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
       if (productIds.size() != storedProducts.size()) {
           throw new ProductPurchaseException("One or more products does not exists");
       }
       var storedRequest = productPurchaseRequestList.stream().sorted(Comparator.comparing(ProductPurchaseRequest::getProductId)).toList();
       var purchaseProducts = new ArrayList<ProductPurchaseResponse>();
       for (int i = 0; i < storedRequest.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = storedRequest.get(i);
            if(product.getAvailableQuantity() < productRequest.getQuantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with id: " + productRequest.getProductId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.getQuantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchaseProducts.add(mapper.toProductPurchaseResponse(product, productRequest.getQuantity()));
        }
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(mapper::toProductResponse)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(mapper::toProductResponse).collect(Collectors.toList());
    }
}
