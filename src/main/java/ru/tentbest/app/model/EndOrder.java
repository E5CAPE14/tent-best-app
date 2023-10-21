package ru.tentbest.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "end_order")
public class EndOrder {

    @Id
    @Column(value = "id")
    private Long id;
    @Column("products_ids")
    private Long[] product_ids;
    @Column("arrival_date")
    private Date arrival_date;
    @Column(value = "phone_number_z")
    private String phone_number_z;
    @Column(value = "fio_z")
    private String fio_z;
    @Column(value = "manager_name")
    private String manager_name;
    @Column(value = "car_number_z")
    private String car_number;
    @CreatedDate
    @Column(value = "created_date")
    private Timestamp created_date;
    @Column(value = "formation_date")
    private Timestamp formation_date;
    @Column("end_date")
    private Timestamp end_date;
    @Column("order_price")
    private Long orderPrice;
}
