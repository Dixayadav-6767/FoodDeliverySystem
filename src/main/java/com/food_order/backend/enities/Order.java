package com.food_order.backend.enities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_table")
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    Long User_id;
    Long TotalPrice;
    String OrderStatus;
    String PaymentStatus;
    String Address;
}
