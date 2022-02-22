package com.simbirsoft.NewYearToyStore.models.entity;


import liquibase.pro.packaged.I;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Entity
//@EqualsAndHashCode(of = {"firstName", "lastName", "email"})
@NoArgsConstructor
@ToString(of = {"id", "customer"})
@Getter
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime created = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;



//    @ElementCollection
//    @Column(name = "quantity", nullable = false)
//    @MapKeyJoinColumn(name = "toy_id")
//    private Map<NewYearToy, Integer> newYearToys = new HashMap<>();
//
//    public Map<NewYearToy, Integer> getNewYearToys() {
//        return Collections.unmodifiableMap(newYearToys);
//    }
//
//    public void addNewYearToy(NewYearToy newYearToy) {
//        newYearToys.merge(newYearToy, 1, (v1, v2) -> v1 + v2);
//    }
//
//    public void removeNewYearToy(NewYearToy newYearToy) {
//        newYearToys.computeIfPresent(newYearToy, (k, v) -> v > 1? v -1 : null);
//    }


}
