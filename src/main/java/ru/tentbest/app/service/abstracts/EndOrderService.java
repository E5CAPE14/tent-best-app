package ru.tentbest.app.service.abstracts;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.EndOrder;
import ru.tentbest.app.model.Orders;

public interface EndOrderService {

    Mono<Void> save(EndOrder entity);
    Mono<EndOrder> findById(Long integer);
    Mono<Boolean> existsById(Long integer);
    Flux<EndOrder> findAll();
    Mono<Void> deleteById(Long integer);
    Mono<Void> delete(EndOrder entity);

}
