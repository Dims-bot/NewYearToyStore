package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "order_details")
public class OrderDetail extends BaseDomainEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "new_year_toy_id")
    NewYearToy newYearToy;

    @Column
    @Min(0)
    @NotNull
    Integer quantity;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;



//   public OrderDetail(){
//
//   }

//    public OrderDetail(Long id, NewYearToy newYearToy,  Integer quantity, Order order) {
//        super(id);
//        this.newYearToy = newYearToy;
//        this.quantity = quantity;
//        this.order = order;
//    }
//
//    public Long getId() {
//       return id;
//    }
//
//    public void setId(Long id){
//       this.id=id;
//    }
//
//
//
//    public NewYearToy getNewYearToy() {
//        return newYearToy;
//    }
//
//
//    public void setNewYearToy(NewYearToy newYearToy) {
//        this.newYearToy = newYearToy;
//    }
//
//    public Integer getQuantity() {
//        return quantity;
//    }
//
//    public void setQuantity(Integer quantity) {
//        this.quantity = quantity;
//    }
//
//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }
}
