package com.food_order.backend.enities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "Item_Table")
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long ItemId;

    String ItemName;

    String ItemDescription;

    @ManyToOne
    @JoinColumn(name = "category_id ")
    Category Category;

    Long price;

    Boolean Is_available;

}
