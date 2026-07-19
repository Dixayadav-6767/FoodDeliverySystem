package com.food_order.backend.enities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_item")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
