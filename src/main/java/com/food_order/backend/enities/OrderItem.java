package com.food_order.backend.enities;

import jakarta.persistence.*;

@Entity
@Table(name= "OrderItem")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long OrderItemId;
    Long OrderId;
    Long ItemId;
    Long Quantiy;
    Long Price_at_item;

    public OrderItem(Long orderItemId, Long orderId, Long itemId, Long quantiy, Long price_at_item) {
        OrderItemId = orderItemId;
        OrderId = orderId;
        ItemId = itemId;
        Quantiy = quantiy;
        Price_at_item = price_at_item;
    }

    public Long getOrderItemId() {
        return OrderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        OrderItemId = orderItemId;
    }

    public Long getOrderId() {
        return OrderId;
    }

    public void setOrderId(Long orderId) {
        OrderId = orderId;
    }

    public Long getItemId() {
        return ItemId;
    }

    public void setItemId(Long itemId) {
        ItemId = itemId;
    }

    public Long getQuantiy() {
        return Quantiy;
    }

    public void setQuantiy(Long quantiy) {
        Quantiy = quantiy;
    }

    public Long getPrice_at_item() {
        return Price_at_item;
    }

    public void setPrice_at_item(Long price_at_item) {
        Price_at_item = price_at_item;
    }
}
