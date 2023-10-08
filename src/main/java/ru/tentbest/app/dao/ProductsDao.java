package ru.tentbest.app.dao;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.Products;

public interface ProductsDao{
    Mono<Void> save(Products entity);
    Mono<Products> findById(Integer integer);
    Mono<Boolean> existsById(Integer integer);
    Flux<Products> findAll();
    Mono<Void> deleteById(Integer integer);
    Mono<Void> delete(Products entity);
}
