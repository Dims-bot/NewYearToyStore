package com.simbirsoft.NewYearToyStore.models.entity;


import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@FieldDefaults(level = AccessLevel.PROTECTED)
@NoArgsConstructor
@Getter
@Setter
@Table(name = "toys")
public class NewYearToy extends BaseDomainEntity {

    @Column
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

    @Column
    BigDecimal price;

}
