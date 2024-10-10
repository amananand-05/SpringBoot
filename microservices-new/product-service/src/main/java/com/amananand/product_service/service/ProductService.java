package com.amananand.product_service.service;

import com.amananand.product_service.dto.ProductRequest;
import com.amananand.product_service.dto.ProductResponse;
import com.amananand.product_service.model.Product;
import com.amananand.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

//    @Autowired
//    private ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product: {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    public ProductResponse getProduct(String id) {
        Optional<Product> product = productRepository.findById(id);

//        if (product.isPresent())
//          return mapToProductResponse(product.get());
//        else
//          return null;

        return product.map(this::mapToProductResponse).orElse(null);
    }

    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
