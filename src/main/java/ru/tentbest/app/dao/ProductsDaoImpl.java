package ru.tentbest.app.dao;

import io.r2dbc.spi.ConnectionFactories;
import io.r2dbc.spi.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.Products;
@Repository
public class ProductsDaoImpl implements ProductsDao{

    @Autowired
    private DatabaseClient databaseClient;

    @Override
    @Transactional
    public Mono<Void> save(Products entity) {
        Mono<Void> voidMono = databaseClient
                .sql("insert into app.products(product_name,product_price,product_description,product_size,product_volume) values (:name,:price,:description,:size,:volume)")
                .bind("name",entity.getProductName())
                .bind("price",entity.getProductPrice())
                .bind("description",entity.getProductDescription())
                .bind("size",entity.getProductSize())
                .bind("volume",entity.getProductVolume())
                .then()
                .log(String.format("Продукт (имя:цена) %s:%d сохранен",entity.getProductName(),entity.getProductPrice()));

        return voidMono;
    }

    @Override
    public Mono<Products> findById(Integer id) {
        Mono<Products> productsMono = databaseClient
                .sql("select id,product_name,product_price,product_description,product_size,product_volume from app.products as p where p.id = :id LIMIT 2")
                .bind("id",id)
                .map(row -> {
                    return new Products(
                            row.get("id", Long.class),
                            row.get("product_name", String.class),
                            row.get("product_description",String.class),
                            row.get("product_price",Long.class),
                            row.get("product_size", String.class),
                            row.get("product_volume", Integer.class));
                }).one();
        return productsMono;
    }

    @Override
    public Mono<Boolean> existsById(Integer integer) {
        return null;
    }

    @Override
    public Flux<Products> findAll() {
        return databaseClient
                .sql("select id,product_name,product_price,product_description,product_size,product_volume from app.products group by (id,product_name,product_price,product_description,product_size,product_volume)")
                .map(row -> {
                    return new Products(
                            row.get("id", Long.class),
                            row.get("product_name", String.class),
                            row.get("product_description",String.class),
                            row.get("product_price",Long.class),
                            row.get("product_size", String.class),
                            row.get("product_volume", Integer.class));
                }).all();
    }

    @Override
    public Mono<Void> deleteById(Integer integer) {
        return null;
    }

    @Override
    public Mono<Void> delete(Products entity) {
        return null;
    }
}
