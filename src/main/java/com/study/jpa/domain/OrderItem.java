package com.study.jpa.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigInteger;
import java.util.List;

@Entity
@Table(name = "order_items")
@Getter
public class OrderItem {
    @Id
    Integer id;
    // 여러 주문상품 엔티티가 하나의 주문과 관계를 맺을 수 있다.
    @ManyToOne
    Order order;
    // 여러 주문상품 엔티티가 하나의 아이템과 관계를 맺을 수 있다.
    @ManyToOne
    Item item;

    // 주문상품 수량
    int count;
    // 상품금액
    BigInteger price;

}
