package ru.tentbest.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.dao.abstracts.ManagerDao;
import ru.tentbest.app.model.EndOrder;
import ru.tentbest.app.model.Orders;
import ru.tentbest.app.model.Products;
import ru.tentbest.app.service.abstracts.EndOrderService;
import ru.tentbest.app.service.abstracts.ManagerService;
import ru.tentbest.app.service.abstracts.OrderService;
import ru.tentbest.app.service.abstracts.ProductsService;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerDao managerDao;
    private final EndOrderService endOrderService;
    private final OrderService orderService;
    private final ProductsService productsService;

    @Autowired
    public ManagerServiceImpl(ManagerDao managerDao, EndOrderService endOrderService,
                              OrderService orderService,
                              ProductsService productsService) {
        this.managerDao = managerDao;
        this.endOrderService = endOrderService;
        this.orderService = orderService;
        this.productsService = productsService;
    }

    @Override
    public Mono<Void> createOrder(Orders entity) {
        return orderService.save(entity);
    }

    @Override
    public Mono<Orders> findOrderById(Long id) {
        return orderService.findById(id);
    }

    @Override
    public Flux<Orders> findOrders() {
        return managerDao.findOpenOrders(SecurityContextHolder.getContext().getAuthentication());
    }

    @Override
    public Mono<Orders> getOrderByNumberZ(String numberZ) {
        return orderService.getOrderByNumberZ(numberZ);
    }


    @Override
    public Mono<Void> closeOrder(Long id) {
        return orderService.closeOrder(id);
    }

    @Override
    public Mono<Products> findProductsById(Integer integer) {
        return productsService.findById(integer);
    }
    @Override
    public Flux<Products> findProducts() {
        return productsService.findAll();
    }

    @Override
    public Mono<EndOrder> findEndOrderById(Long integer) {
        return endOrderService.findById(integer);
    }

    @Override
    public Flux<EndOrder> findEndOrders() {
        return managerDao.findEndedOrder(SecurityContextHolder.getContext().getAuthentication());
    }
}
