package com.food_order.backend.enities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "User_table")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long User_id;
    String name;
    String email;
//    String phoneNo;
//    String address;

    @CreationTimestamp
    String createdAt;
    String password;
}
