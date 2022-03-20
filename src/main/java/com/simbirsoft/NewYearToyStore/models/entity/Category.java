package com.simbirsoft.NewYearToyStore.models.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;


@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "categories")
public class Category extends BaseDomainEntity {

    @Column
    @NotBlank
    String categoryName;

//    public Category() {
//
//    }
//
//    public Category(Long id, String categoryName) {
//        super(id);
//        this.categoryName = categoryName;
//    }
//
//    public String getCategoryName() {
//        return categoryName;
//    }
//
//    public void setCategoryName(String categoryName) {
//        this.categoryName = categoryName;
//    }
//
//    public void SetId(Long id) {
//        this.id = id;
//    }
//
//    public Long getId() {
//        return id;
//    }

}
