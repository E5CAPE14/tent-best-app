package ru.tentbest.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.dao.ProductsDao;
import ru.tentbest.app.model.Products;

import java.security.Principal;
import java.time.Duration;
import java.time.temporal.TemporalUnit;
import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class Controller {

    @Autowired
    private ProductsDao productsDao;
    @PostMapping("/save")
    public Mono<ResponseEntity<Void>> save(@RequestBody Products products){
        return productsDao.save(products).log().map(ResponseEntity::ok);
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<?>> findById(@PathVariable("id") Integer value,@AuthenticationPrincipal Authentication authentication){
        String name = authentication.getAuthorities().toString()
                .replace("ROLE_","")
                .replace("[","")
                .replace("]","");
        return productsDao.findById(value).log(String.format("%s:%s вызвал метод findById(%d)",authentication.getName(),name,value)).onErrorResume(x -> {
            return Mono.empty();
        }).map(ResponseEntity::ok);
    }

    @GetMapping()
    public Flux<Products> findAll(){
        return productsDao.findAll()
                .timeout(Duration.ofMillis(10000)).log("Был вызван метод Controller::findAll");
    }
}
