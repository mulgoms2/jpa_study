package com.study.jpa.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();
    private LocalDateTime orderDate;
    private BigInteger orderPrice;
    @Enumerated(EnumType.STRING)
    private Status status;

    @Builder
    public Order(LocalDateTime orderDate, BigInteger orderPrice, Status status) {
        this.orderDate = orderDate;
        this.orderPrice = orderPrice;
        this.status = status;
    }

    public static Order makeOrder(OrderItem... orderItems) {
        BigInteger totalPrice = BigInteger.ZERO;
        for (OrderItem orderItem : orderItems) {
            int orderCount = orderItem.getCount();
            BigInteger itemPrice = orderItem.getPrice();
            totalPrice.add(itemPrice.multiply(BigInteger.valueOf(orderCount)));
        }
        return new Order(LocalDateTime.now(), totalPrice, Status.ORDER);
    }

    public boolean addItem(OrderItem... orderItems) {
        Collections.addAll(this.orderItems, orderItems);
        return true;
    }

    public enum Status {
        ORDER, CANCEL
    }
}
