package com.food_order.backend.enities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    String description;

    String image;

    @ManyToOne
    @JoinColumn(name = "category_id ")
    Category category;

    Long price;

    Boolean is_available;

}
