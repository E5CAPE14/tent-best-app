package ru.tentbest.app.dao.abstracts;

import org.springframework.security.core.Authentication;
import reactor.core.publisher.Flux;
import ru.tentbest.app.model.EndOrder;
import ru.tentbest.app.model.Orders;

public interface ManagerDao {

    Flux<Orders> findOpenOrders(Authentication authentication);
    Flux<EndOrder> findEndedOrder(Authentication authentication);

}
