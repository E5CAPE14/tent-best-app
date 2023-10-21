package ru.tentbest.app.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.Products;
import ru.tentbest.app.service.abstracts.ProductsService;
@Service
public class ProductServiceImpl implements ProductsService {
    @Override
    public Mono<Void> save(Products entity) {
        return null;
    }

    @Override
    public Mono<Products> findById(Integer integer) {
        return null;
    }

    @Override
    public Mono<Boolean> existsById(Integer integer) {
        return null;
    }

    @Override
    public Flux<Products> findAll() {
        return null;
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
