package ru.tentbest.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.dao.abstracts.OrdersDao;
import ru.tentbest.app.model.EndOrder;
import ru.tentbest.app.model.Orders;
import ru.tentbest.app.model.Products;

import java.sql.Timestamp;
import java.util.Date;

@Repository
public class OrdersDaoImpl implements OrdersDao {

    @Autowired
    private DatabaseClient databaseClient;

    @Override
    public Mono<Void> save(Orders entity) {
        return databaseClient
                .sql("insert into order(products_ids,arrival_date,phone_number_z,fio_z,manager_name,car_number_z) values (:products_ids,:arrival_date,:phone_number_z,:fio_z,:manager_name,:car_number_z)")
                .bind("products_ids",entity.getProductIds())
                .bind("arrival_date",entity.getArrivalDate())
                .bind("phone_number_z",entity.getPhoneNumberZ())
                .bind("fio_z",entity.getFioZ())
                .bind("manager_name",entity.getManagerName())
                .bind("car_number_z",entity.getCarNumber())
                .then();
    }

    @Override
    public Mono<Orders> findById(Long id) {
        Mono<Orders> productsMono = databaseClient
                .sql("select id,products_ids,arrival_date,phone_number_z,fio_z,manager_name,car_number_z,created_date,formation_date from app.order as o where o.id = :id LIMIT 2")
                .bind("id",id)
                .map(row -> {
                    return new Orders(
                            row.get("products_ids",Long[].class),
                            row.get("arrival_date", Date.class),
                            row.get("phone_number_z",String.class),
                            row.get("fio_z",String.class),
                            row.get("manager_name",String.class),
                            row.get("car_number_z",String.class),
                            row.get("created_date", Timestamp.class),
                            row.get("formation_date", Timestamp.class)
                    );
                }).one();
        return productsMono;
    }

    @Override
    public Mono<Boolean> existsById(Long integer) {
        return null;
    }

    @Override
    public Flux<Orders> findAll() {
        return databaseClient.sql("select id,products_ids,arrival_date,phone_number_z,fio_z,manager_name,car_number_z,created_date,formation_date from app.order group by id")
                .map(row -> {
                    return new Orders(
                            row.get("products_ids",Long[].class),
                            row.get("arrival_date", Date.class),
                            row.get("phone_number_z",String.class),
                            row.get("fio_z",String.class),
                            row.get("manager_name",String.class),
                            row.get("car_number_z",String.class),
                            row.get("created_date", Timestamp.class),
                            row.get("formation_date", Timestamp.class)
                    );
                }).all();
    }

    @Override
    public Mono<Void> deleteById(Long integer) {
        StringBuilder sqlQuery = new StringBuilder(
                "delete from app.orders as o where o.id = :id"
        );
        return databaseClient
                .sql(sqlQuery.toString())
                .bind("id",integer)
                .then();
    }

    @Override
    public Mono<Void> delete(Orders entity) {
        return deleteById(entity.getId());
    }

    @Override
    public Mono<Orders> getOrderByNumberZ(String numberZ) {
        StringBuilder sqlQuery = new StringBuilder(
                "select id,products_ids,arrival_date,phone_number_z,fio_z,manager_name,car_number_z,created_date,formation_date " +
                        " from app.orders as o where o.phone_number_z = ")
                .append(numberZ)
                .append("group by id,created_date");


        return databaseClient.sql(sqlQuery.toString())
                .map(row -> {
                    return new Orders(
                            row.get("products_ids",Long[].class),
                            row.get("arrival_date", Date.class),
                            row.get("phone_number_z",String.class),
                            row.get("fio_z",String.class),
                            row.get("manager_name",String.class),
                            row.get("car_number_z",String.class),
                            row.get("created_date", Timestamp.class),
                            row.get("formation_date", Timestamp.class)
                    );
                }).one();
    }
}
