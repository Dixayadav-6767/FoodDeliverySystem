package com.food_order.backend.enities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Table(name= "order_item")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "order_table_id")
    Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;

    Long quantity;
    Long price_at_item;
}
