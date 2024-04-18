package com.study.jpa.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private UserType userType;
    private int age;
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
    @Lob
    private String description;
    // 연관관계의 주인이 아니지만 동시에 데이터베이스 상에서는 부모테이블이므로 케스케이드 옵션이 이곳에 들어가는게 자연스럽다.
    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    final private List<Order> orders = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    private Team team;

    public enum UserType {
        ADMIN, USER
    }

    public Order order(Item... items) {
        // 회원이 상품을 주문한다.
        Order order = Order.builder()
                .orderDate(LocalDateTime.now())
                .build();

        // 해당 아이템을 포함한 주문이 생성되어 반환된다.
        order.addItem(items);

        // 회원의 주문 목록에 주문을 추가한다.
        this.orders.add(order);

        // 생성 된 주문을 반환한다.
        return order;
    }
}
