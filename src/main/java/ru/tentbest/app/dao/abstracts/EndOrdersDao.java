package ru.tentbest.app.dao.abstracts;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.Orders;

public interface EndOrdersDao {

    Mono<Void> save(Orders entity);
    Mono<Orders> findById(Long integer);
    Mono<Boolean> existsById(Long integer);
    Flux<Orders> findAll();
    Mono<Void> deleteById(Long integer);
    Mono<Void> delete(Orders entity);

}
