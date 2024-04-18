package com.study.jpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GeneratedColumn;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
class Item {
    // jpa h2 의 기본 전략을
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @ManyToOne
//    private Order order;
    private String name;
    private BigInteger price;
    private Integer quantity;
    private LocalDateTime createdAt;
}
