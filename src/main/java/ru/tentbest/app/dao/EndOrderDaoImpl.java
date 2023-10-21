package ru.tentbest.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.tentbest.app.dao.abstracts.EndOrdersDao;
import ru.tentbest.app.model.EndOrder;
import ru.tentbest.app.model.Orders;

import java.sql.Timestamp;
import java.util.Date;

@Repository
public class EndOrderDaoImpl implements EndOrdersDao {

    private final DatabaseClient databaseClient;

    @Autowired
    public EndOrderDaoImpl(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }

    @Override
    public Mono<Void> save(EndOrder entity) {
        StringBuilder sqlQuery = new StringBuilder(
                "insert into end_order(products_ids,arrival_date,phone_number_z,fio_z,manager_name,car_number_z) " +
                        "values (:product_ids,:arrival_date,:phone_number_z,:fio_z,:manager_name,:car_number_z,:created_date,:formation_date,now(),:order_price)"
        );
        return databaseClient
                .sql(sqlQuery.toString())
                .bind("products_ids",entity.getProduct_ids())
                .bind("arrival_date",entity.getArrival_date())
                .bind("phone_number_z",entity.getPhone_number_z())
                .bind("fio_z",entity.getFio_z())
                .bind("manager_name",entity.getManager_name())
                .bind("car_number_z",entity.getCar_number())
                .bind("created_date",entity.getCreated_date())
                .bind("formation_date",entity.getFormation_date())
                .bind("order_price",null)
                .then();
    }

    @Override
    public Mono<EndOrder> findById(Long integer) {
        StringBuilder sqlQuery = new StringBuilder(
                "select id,product_ids,arrival_date,phone_number_z,fio_z,manager_name,car_number_z,created_date,formation_date,end_date,order_price" +
                        "from app.end_order as o where o.id = :id group by (id,end_date)"
        );
        return databaseClient
                .sql(sqlQuery.toString())
                .bind("id",integer)
                .map(row -> {
                    return new EndOrder(
                            row.get("id",Long.class),
                            row.get("products_ids", Long[].class),
                            row.get("arrival_date", Date.class),
                            row.get("phone_number_z",String.class),
                            row.get("fio_z",String.class),
                            row.get("manager_name",String.class),
                            row.get("car_number_z",String.class),
                            row.get("created_date", Timestamp.class),
                            row.get("formation_date", Timestamp.class),
                            row.get("end_date",Timestamp.class),
                            row.get("order_price", Long.class)
                    );
                }).one();
    }

    @Override
    public Mono<Boolean> existsById(Long integer) {
        return null;
    }

    @Override
    public Flux<EndOrder> findAll() {
        StringBuilder sqlQuery = new StringBuilder(
                "select id,product_ids,arrival_date,phone_number_z,fio_z,manager_name,car_number_z,created_date,formation_date,end_date,order_price" +
                        "from app.end_order group by (id,end_date)"
        );
        return databaseClient
                .sql(sqlQuery.toString())
                .map(row -> {
                    return new EndOrder(
                            row.get("id",Long.class),
                            row.get("products_ids", Long[].class),
                            row.get("arrival_date", Date.class),
                            row.get("phone_number_z",String.class),
                            row.get("fio_z",String.class),
                            row.get("manager_name",String.class),
                            row.get("car_number_z",String.class),
                            row.get("created_date", Timestamp.class),
                            row.get("formation_date", Timestamp.class),
                            row.get("end_date",Timestamp.class),
                            row.get("order_price", Long.class)
                    );
                }).all();
    }

    @Override
    public Mono<Void> deleteById(Long integer) {
        StringBuilder sqlQuery = new StringBuilder(
                "delete from app.end_order as o where o.id = :id"
        );
        return databaseClient
                .sql(sqlQuery.toString())
                .bind("id",integer)
                .then();
    }

    @Override
    public Mono<Void> delete(EndOrder entity) {
        return deleteById(entity.getId());
    }

    @Override
    public Flux<EndOrder> getEndOrdersByManagerName(String... managerName) {
        StringBuilder stringBuilder = new StringBuilder(
                "select id,product_ids,arrival_date,phone_number_z,fio_z,manager_name,car_number_z,created_date,formation_date,end_date,order_price from app.end_orders as o where o.manager_name in ("
        );
        for (int i = 0;i < managerName.length;i++){
            if(i == managerName.length-1){
                stringBuilder.append(managerName[i]).append(") group by id");
                break;
            }
            stringBuilder.append(managerName[i]).append(", ");
        }

        return databaseClient.sql(stringBuilder.toString())
                .map(row -> {
                    return new EndOrder(
                            row.get("id",Long.class),
                            row.get("products_ids", Long[].class),
                            row.get("arrival_date", Date.class),
                            row.get("phone_number_z",String.class),
                            row.get("fio_z",String.class),
                            row.get("manager_name",String.class),
                            row.get("car_number_z",String.class),
                            row.get("created_date", Timestamp.class),
                            row.get("formation_date", Timestamp.class),
                            row.get("end_date",Timestamp.class),
                            row.get("order_price", Long.class)
                    );
        }).all();
    }

    @Override
    public Mono<EndOrder> getEndOrderByNumberZ(String numberZ) {
        StringBuilder sqlQuery = new StringBuilder(
                "select id,product_ids,arrival_date,phone_number_z,fio_z,manager_name,car_number_z,created_date,formation_date,end_date,order_price" +
                        " from app.end_orders as o where o.phone_number_z = ")
                .append(numberZ)
                .append("group by id,end_date");


        return databaseClient.sql(sqlQuery.toString())
                .map(row -> {
                    return new EndOrder(
                            row.get("id", Long.class),
                            row.get("products_ids", Long[].class),
                            row.get("arrival_date", Date.class),
                            row.get("phone_number_z", String.class),
                            row.get("fio_z", String.class),
                            row.get("manager_name", String.class),
                            row.get("car_number_z", String.class),
                            row.get("created_date", Timestamp.class),
                            row.get("formation_date", Timestamp.class),
                            row.get("end_date", Timestamp.class),
                            row.get("order_price", Long.class)
                    );
                }).one();
    }
}
