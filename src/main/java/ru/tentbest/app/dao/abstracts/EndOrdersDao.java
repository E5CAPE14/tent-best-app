package ru.tentbest.app.dao.abstracts;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.EndOrder;

public interface EndOrdersDao {

    Mono<Void> save(EndOrder entity);
    Mono<EndOrder> findById(Long integer);
    Mono<Boolean> existsById(Long integer);
    Flux<EndOrder> findAll();
    Mono<Void> deleteById(Long integer);
    Mono<Void> delete(EndOrder entity);
    Flux<EndOrder> getEndOrdersByManagerName(String... managerName);
    Mono<EndOrder> getEndOrderByNumberZ(String numberZ);
}
