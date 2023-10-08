package ru.tentbest.app.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@ToString
@Table(name = "products")
public class Products {
    @Id
    private Long id;
    @Column(value = "product_name")
    private String productName;
    @Column(value = "product_description")
    private String productDescription;
    @Column(value = "product_price")
    private Long productPrice;
    @Column(value = "product_size")
    private String productSize;
    @Column(value = "product_volume")
    private Integer productVolume;

    public Products(Long id, String productName, String productDescription, Long productPrice, String productSize, Integer productVolume) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productVolume = productVolume;
    }

    public Products(String productName, String productDescription, Long productPrice, String productSize, Integer productVolume) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.productPrice = productPrice;
        this.productSize = productSize;
        this.productVolume = productVolume;
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

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Integer getProductVolume() {
        return productVolume;
    }

    public void setProductVolume(Integer productVolume) {
        this.productVolume = productVolume;
    }
}
