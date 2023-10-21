package ru.tentbest.app.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.tentbest.app.dao.abstracts.ManagerDao;
import ru.tentbest.app.model.EndOrder;
import ru.tentbest.app.model.Orders;

import java.sql.Timestamp;
import java.util.Date;

@Repository
public class ManagerDaoImpl implements ManagerDao {
    private final DatabaseClient databaseClient;

    @Autowired
    public ManagerDaoImpl(DatabaseClient databaseClient) {
        this.databaseClient = databaseClient;
    }


    @Override
    public Flux<Orders> findOpenOrders(@AuthenticationPrincipal Authentication authentication) {
        StringBuilder stringBuilder = new StringBuilder(
                "select id,products_ids,arrival_date,phone_number_z,fio_z,manager_name,car_number_z,created_date,formation_date from app.orders as o where o.manager_name="
        ).append(authentication.getName()).append("group by id,created_date");
        authentication.getName();


        return databaseClient.sql(stringBuilder.toString()).map(row -> {
            return new Orders(
                    row.get("products_ids",Long[].class),
                    row.get("arrival_date", Date.class),
                    row.get("phone_number_z",String.class),
                    row.get("fio_z",String.class),
                    row.get("manager_name",String.class),
                    row.get("car_number_z",String.class),
                    row.get("created_date", Timestamp.class),
                    row.get("formation_date", Timestamp.class)
            );}).all();
    }

    @Override
    public Flux<EndOrder> findEndedOrder(Authentication authentication) {
        StringBuilder sqlQuery = new StringBuilder(
                "select id,product_ids,arrival_date,phone_number_z,fio_z,manager_name,car_number_z,created_date,formation_date,end_date,order_price" +
                        "from app.end_order as eo where eo.manager_name =" + authentication + "group by (id,end_date)"
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
}
