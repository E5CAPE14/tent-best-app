package ru.tentbest.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.model.EndOrder;
import ru.tentbest.app.model.Orders;
import ru.tentbest.app.model.Products;
import ru.tentbest.app.service.abstracts.EndOrderService;
import ru.tentbest.app.service.abstracts.ManagerService;
import ru.tentbest.app.service.abstracts.OrderService;
import ru.tentbest.app.service.abstracts.ProductsService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ManagerController {

    @Autowired
    private ManagerService managerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private EndOrderService endOrderService;
    @Autowired
    private ProductsService productsService;

    @GetMapping("/open-orders")
    public Flux<Orders> getOpenOrders(){
        return orderService.findAll();
    }
    @GetMapping("/my-orders")
    public Flux<Orders> getOrdersByManager() {
        return managerService.findOrders();
    }

    @GetMapping("/open-orders/{id}")
    public Mono<ResponseEntity<Orders>> getOrderById(@PathVariable(value = "id") Long id){
        return managerService.findOrderById(id).map(ResponseEntity::ok);
    }

    @PostMapping("/open-orders")
    public Mono<Void> saveOrders(@RequestBody Orders orders){
        return managerService.createOrder(orders);
    }

    @DeleteMapping("/open-orders")
    public Mono<Void> deleteOrders(@RequestBody Long id){
        return orderService.deleteById(id);
    }

    @PutMapping("/close-orders")
    public Mono<Void> closeOrders(@RequestBody Long id){
        return managerService.closeOrder(id);
    }

    @GetMapping("/find-orders/{phone}")
    public Mono<Orders> findOrderByPhoneNumberZ(@PathVariable(value = "phone") String phone){
        return managerService.getOrderByNumberZ(phone);
    }
    @PostMapping("/save-product")
    public Mono<Void> saveProduct(@RequestBody Products entity){
        return productsService.save(entity);
    };
    @GetMapping("/find-product/{id}")
    public Mono<Products> findProductsById(@PathVariable Integer integer){
        return productsService.findById(integer);
    }
    @GetMapping("/find-products")
    public Flux<Products> findProducts(){
        return productsService.findAll();
    }
    @DeleteMapping("/delete-product")
    public Mono<Void> deleteProduct(@RequestBody Products entity){
        return productsService.delete(entity);
    }
    @PostMapping("/save-end-order")
    public Mono<Void> saveEndOrder(@RequestBody EndOrder entity){
        return endOrderService.save(entity);
    }
    @GetMapping("/find-end-order/{id}")
    public Mono<EndOrder> findEndOrderById(Long integer){
        return managerService.findEndOrderById(integer);
    }
    @GetMapping("/find-end-orders")
    public Flux<EndOrder> findEndOrders(){
        return managerService.findEndOrders();
    }
    @DeleteMapping("/delete-end-order")
    public Mono<Void> deleteEndOrder(@RequestBody EndOrder entity){
        return endOrderService.delete(entity);
    }

}
