package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.strategy.DiscountContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final DiscountContext discountContext;

    public ProductService(ProductRepository productRepository, DiscountContext discountContext) {
        this.productRepository = productRepository;
        this.discountContext = discountContext;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + id));
    }

    @Transactional
    public Product saveProduct(Product product) {
        if (product.getDetail() != null) {
            product.getDetail().setProduct(product);
        }
        if (product.getReviews() != null && !product.getReviews().isEmpty()) {
        product.getReviews().forEach(review -> review.setProduct(product));
        }
        return productRepository.save(product);
    }

    @Transactional
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);
        existing.setName(updatedProduct.getName());
        existing.setCategory(updatedProduct.getCategory());
        existing.setBrand(updatedProduct.getBrand());
        existing.setStock(updatedProduct.getStock());
        existing.setPrice(updatedProduct.getPrice());
        existing.setDiscountType(updatedProduct.getDiscountType());

        if (updatedProduct.getDetail() != null) {
            if (existing.getDetail() == null) {
                existing.setDetail(updatedProduct.getDetail());
            } else {
                existing.getDetail().setDescription(updatedProduct.getDetail().getDescription());
                existing.getDetail().setWarranty(updatedProduct.getDetail().getWarranty());
                existing.getDetail().setWeight(updatedProduct.getDetail().getWeight());
                existing.getDetail().setDimensions(updatedProduct.getDetail().getDimensions());
                existing.getDetail().setManufacturedCountry(updatedProduct.getDetail().getManufacturedCountry());
            }
        }
        return productRepository.save(existing);
    }

    @Transactional
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public double calculateEffectivePrice(Product product) {
        return discountContext.calculateFinalPrice(product.getPrice(), product.getDiscountType());
    }

    
}