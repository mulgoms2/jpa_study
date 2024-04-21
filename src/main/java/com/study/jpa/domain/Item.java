package com.study.jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GeneratedColumn;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
class Item {
    // jpa h2 의 기본 전략을
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // many 가 항상 연관관계의 주인이다.
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Order order;
    private String name;
    private BigInteger price;
    private Integer quantity;
    private LocalDateTime createdAt;

    @Builder
    public Item(Order order, String name, BigInteger price, Integer quantity, LocalDateTime createdAt) {
        this.order = order;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }
}
