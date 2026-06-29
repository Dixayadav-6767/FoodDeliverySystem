package com.food_order.backend.enities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "User_table")
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long User_id;
    String name;
    String email;
    String phoneNo;
    String address;
    String createdAt;



}
