package ru.tentbest.app.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.tentbest.app.service.CalculationService;

import java.sql.Timestamp;
import java.util.Date;

@ToString
@Table(name = "order")
public class Orders {

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

    public Orders(Long[] product_id, Date arrival_date, String phone_number_z, String fio_z, String manager_name, String car_number, Timestamp created_date, Timestamp formation_date) {
        this.product_ids = product_id;
        this.arrival_date = arrival_date;
        this.phone_number_z = phone_number_z;
        this.fio_z = fio_z;
        this.manager_name = manager_name;
        this.car_number = car_number;
        this.created_date = created_date;
        this.formation_date = formation_date;
    }

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public Long[] getProductIds() {
        return product_ids;
    }

    public void setProductIds(Long[] product_ids) {
        this.product_ids = product_ids;
    }

    public Date getArrivalDate() {
        return arrival_date;
    }

    public void setArrivalDate(Date arrival_date) {
        this.arrival_date = arrival_date;
    }

    public String getPhoneNumberZ() {
        return phone_number_z;
    }

    public void setPhoneNumberZ(String phone_number_z) {
        this.phone_number_z = phone_number_z;
    }

    public String getFioZ() {
        return fio_z;
    }

    public void setFioZ(String fio_z) {
        this.fio_z = fio_z;
    }

    public String getManagerName() {
        return manager_name;
    }

    public void setManagerName(String manager_name) {
        this.manager_name = manager_name;
    }

    public String getCarNumber() {
        return car_number;
    }

    public void setCarNumber(String car_number) {
        this.car_number = car_number;
    }

    public Timestamp getCreatedDate() {
        return created_date;
    }

    public void setCreatedDate(Timestamp created_date) {
        this.created_date = created_date;
    }

    public Timestamp getFormationDate() {
        return formation_date;
    }

    public void setFormationDate(Timestamp formation_date) {
        this.formation_date = formation_date;
    }
    private Double checkSum(CalculationService service){
        return service.calc(product_ids);
    }
}
