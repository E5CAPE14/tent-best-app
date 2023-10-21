package ru.tentbest.app.service.abstracts;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.Orders;
import ru.tentbest.app.model.Products;

public interface ProductsService {
    Mono<Void> save(Products entity);
    Mono<Products> findById(Integer integer);
    Mono<Boolean> existsById(Integer integer);
    Flux<Products> findAll();
    Mono<Void> deleteById(Integer integer);
    Mono<Void> delete(Products entity);

}
