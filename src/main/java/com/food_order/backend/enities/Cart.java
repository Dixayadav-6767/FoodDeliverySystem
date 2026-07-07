package com.food_order.backend.enities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name = "cart")
@AllArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@Builder
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "cart")
    List<CartItem> cartItems;
}
