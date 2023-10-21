package ru.tentbest.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.dao.abstracts.EndOrdersDao;
import ru.tentbest.app.model.EndOrder;
import ru.tentbest.app.service.abstracts.EndOrderService;
@Service
public class EndOrderServiceImpl implements EndOrderService {

    private final EndOrdersDao endOrdersDao;

    @Autowired
    public EndOrderServiceImpl(EndOrdersDao endOrdersDao) {
        this.endOrdersDao = endOrdersDao;
    }

    @Override
    public Mono<Void> save(EndOrder entity) {
        return endOrdersDao.save(entity);
    }

    @Override
    public Mono<EndOrder> findById(Long integer) {
        return null;
    }

    @Override
    public Mono<Boolean> existsById(Long integer) {
        return null;
    }

    @Override
    public Flux<EndOrder> findAll() {
        return null;
    }

    @Override
    public Mono<Void> deleteById(Long integer) {
        return null;
    }

    @Override
    public Mono<Void> delete(EndOrder entity) {
        return null;
    }
}
