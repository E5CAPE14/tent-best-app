package ru.tentbest.app.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.lang.NonNull;
import ru.tentbest.app.model.abstracts.AbstractProduct;

@ToString
@Table(name = "products")
public class Products extends AbstractProduct {

    @Column(value = "product_size")
    private String productSize;
    @Column(value = "product_volume")
    private Integer productVolume;

    public Products(Long id, String productName, String productDescription, Long productPrice, String productSize, Integer productVolume) {
        super(id, productName, productDescription, productPrice);
        this.productSize = productSize;
        this.productVolume = productVolume;
    }

    public Products() {
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
