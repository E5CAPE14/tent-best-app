package ru.tentbest.app.dao.abstracts;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.Orders;
import ru.tentbest.app.model.Products;

public interface OrdersDao {
    Mono<Void> save(Orders entity);
    Mono<Orders> findById(Long integer);
    Mono<Boolean> existsById(Long integer);
    Flux<Orders> findAll();
    Mono<Void> deleteById(Long integer);
    Mono<Void> delete(Orders entity);
    Mono<Orders> getOrderByNumberZ(String numberZ);
}
