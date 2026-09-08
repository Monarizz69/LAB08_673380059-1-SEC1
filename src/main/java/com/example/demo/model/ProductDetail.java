package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "product_details")
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "warranty")
    private String warranty;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "dimensions")
    private String dimensions;

    @Column(name = "manufactured_country")
    private String manufacturedCountry;

    @OneToOne(mappedBy = "detail")
    private Product product;

    public ProductDetail() {}

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getWarranty() { return warranty; }
    public void setWarranty(String warranty) { this.warranty = warranty; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public String getDimensions() { return dimensions; }
    public void setDimensions(String dimensions) { this.dimensions = dimensions; }

    public String getManufacturedCountry() { return manufacturedCountry; }
    public void setManufacturedCountry(String manufacturedCountry) { this.manufacturedCountry = manufacturedCountry; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}