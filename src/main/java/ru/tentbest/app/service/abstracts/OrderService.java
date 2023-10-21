package ru.tentbest.app.service.abstracts;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.Orders;

public interface OrderService{

    Mono<Void> save(Orders entity);
    Mono<Orders> findById(Long integer);
    Mono<Boolean> existsById(Integer integer);
    Flux<Orders> findAll();
    Mono<Void> deleteById(Long integer);
    Mono<Void> delete(Orders entity);
    Flux<Orders> getAllOrdersByManagerName(String... managerName);
    Mono<Orders> getOrderByNumberZ(String numberZ);
    Mono<Void> closeOrder(Long id);
}
