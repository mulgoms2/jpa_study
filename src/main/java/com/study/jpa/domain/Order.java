package com.study.jpa.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private final List<Item> items = new ArrayList<>();
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

    public boolean addItem(Item... items) {
        Collections.addAll(this.items, items);
        return true;
    }

    public enum Status {
        ORDER, CANCEL
    }
}
