package ru.tentbest.app.dao.abstracts;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.Products;

public interface ProductsDao{
    Mono<Void> save(Products entity);
    Mono<Products> findById(Long integer);
    Mono<Boolean> existsById(Long integer);
    Flux<Products> findAll();
    Mono<Void> deleteById(Long integer);
    Mono<Void> delete(Products entity);
}
