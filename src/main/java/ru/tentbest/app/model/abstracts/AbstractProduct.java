package ru.tentbest.app.model.abstracts;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

public abstract class AbstractProduct {
    @Id
    private Long id;
    @Column(value = "product_name")
    private String productName;
    @Column(value = "product_description")
    private String productDescription;
    @Column(value = "product_price")
    private Long productPrice;

    public AbstractProduct(Long id, String productName, String productDescription, Long productPrice) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
    }

    public AbstractProduct() {
    }

    public Long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }
}
