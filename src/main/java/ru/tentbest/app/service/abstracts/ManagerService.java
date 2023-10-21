package ru.tentbest.app.service.abstracts;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.EndOrder;
import ru.tentbest.app.model.Orders;
import ru.tentbest.app.model.Products;

public interface ManagerService {

    public Mono<Void> createOrder(Orders entity);
    public Mono<Orders> findOrderById(Long integer);
    public Flux<Orders> findOrders();
    public Mono<Orders> getOrderByNumberZ(String numberZ);
    public Mono<Void> closeOrder(Long id);
    Mono<Products> findProductsById(Integer integer);
    Flux<Products> findProducts();
    Mono<EndOrder> findEndOrderById(Long integer);
    Flux<EndOrder> findEndOrders();
}
