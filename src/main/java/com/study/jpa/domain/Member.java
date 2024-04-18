package com.study.jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.usertype.UserType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
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
//    @OneToMany
//    final private List<Order> orderList = Collections.emptyList();
//    final private List<Order> orderList = new ArrayList<>();
    /* member객체가 perstis 되기 전에 team 객체가 먼저 persist 되어야 한다. 그러나 cascade.persist 옵션을 주면 멤버가
    영속성 상태가 되기 전에 team도 함께 영속성 컨텍스트에 등록한다.
     */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    private Team team;

    public enum UserType {
        ADMIN, USER
    }

//    public Order order(Item... items) {
//        // 회원이 상품을 주문한다.
//        Order order = Order.builder()
//                .id(1L)
//                .orderDate(LocalDateTime.now())
//                .build();
//
//        // 해당 아이템을 포함한 주문이 생성되어 반환된다.
////        order.addItem(items);
//
//        // 회원의 주문 목록에 주문을 추가한다.
//        this.orderList.add(order);
//
//        // 생성 된 주문을 반환한다.
//        return order;
//    }
}
