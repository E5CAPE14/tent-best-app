package ru.tentbest.app.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.Orders;
import ru.tentbest.app.service.abstracts.OrderService;
@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public Mono<Void> save(Orders entity) {
        return null;
    }

    @Override
    public Mono<Orders> findById(Long integer) {
        return null;
    }

    @Override
    public Mono<Boolean> existsById(Integer integer) {
        return null;
    }

    @Override
    public Flux<Orders> findAll() {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Long integer) {
        return null;
    }

    @Override
    public Mono<Void> delete(Orders entity) {
        return deleteById(entity.getId());
    }

    @Override
    public Flux<Orders> getAllOrdersByManagerName(String... managerName) {
        return null;
    }

    @Override
    public Mono<Orders> getOrderByNumberZ(String numberZ) {
        return null;
    }

    @Override
    public Mono<Void> closeOrder(Long id) {
        return null;
    }
}
