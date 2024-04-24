package com.study.jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.usertype.UserType;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "members")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@SuperBuilder
public class Member extends CommonDate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private int age;

    @Lob
    private String description;
    // 연관관계의 주인이 아니지만 동시에 데이터베이스 상에서는 부모테이블이므로 케스케이드 옵션이 이곳에 들어가는게 자연스럽다.
    @OneToMany(mappedBy = "member", cascade = CascadeType.PERSIST)
    final private List<Order> orders = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "team_id")
    private Team team;

    @Embedded
    private Address address;

    @ElementCollection
    private List<Address> addresses;

    public enum UserType {
        ADMIN, USER
    }

    //    회원이 상품을 주문하면 주문상품이 되는게 자연스럽지 않을까?
    // 주문을 만드는데에 회원의 정보가 필요하다고도 볼 수 있다.
//    public Order order(Item... items, int count) {
//        // 회원이 상품을 주문한다.
//
//        // 아이템을 주문 아이템으로 변경한다.
//
//        Order order = Order.makeOrder(orderItems);
//
//        // 회원의 주문 목록에 주문을 추가한다.
//        this.orders.add(order);
//        order.setMember(this);
//
//        // 생성 된 주문을 반환한다.
//        return order;
//    }
}
