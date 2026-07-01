package com.food_order.backend.enities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Category_table")
@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;

}