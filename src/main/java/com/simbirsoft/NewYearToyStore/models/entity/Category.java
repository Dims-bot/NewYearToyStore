package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;


@Entity
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PROTECTED)
@Getter
@Setter
@Table(name = "categories")
public class Category extends BaseDomainEntity {
    
    @Column
    String categoryName;

}
