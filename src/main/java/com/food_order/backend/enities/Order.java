package com.food_order.backend.enities;

import jakarta.persistence.*;

@Entity
@Table(name = "OrderTable")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long OrderId;
    Long User_id;
    Long TotalPrice;
    String OrderStatus;
    String PaymentStatus;
    String Address;

    public Order(){

    }

    public Order(Long orderId, Long user_id, Long totalPrice, String orderStatus, String paymentStatus, String address) {
        OrderId = orderId;
        User_id = user_id;
        TotalPrice = totalPrice;
        OrderStatus = orderStatus;
        PaymentStatus = paymentStatus;
        Address = address;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public Long getUser_id() {
        return User_id;
    }

    public void setUser_id(Long user_id) {
        User_id = user_id;
    }

    public Long getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        TotalPrice = totalPrice;
    }

    public String getOrderStatus() {
        return OrderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        OrderStatus = orderStatus;
    }

    public String getPaymentStatus() {
        return PaymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        PaymentStatus = paymentStatus;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
