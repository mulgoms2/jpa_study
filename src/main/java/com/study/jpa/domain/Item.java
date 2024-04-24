package com.study.jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GeneratedColumn;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 관계란 엔티티 : 엔티티 간에 형성된다.
    // 아이템(엔티티)이 여러 주문상품(엔티티) 에 공통으로 포함될 수 있다.
    // 아이템 1 : 주문상품 N
    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems;
    private String name;
    private BigInteger price;
    private Integer quantity;
    private LocalDateTime createdAt;

    @Builder
    public Item(String name, BigInteger price, Integer quantity, LocalDateTime createdAt) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.createdAt = createdAt;
    }
}
