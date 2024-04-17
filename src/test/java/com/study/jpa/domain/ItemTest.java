package com.study.jpa.domain;

import jakarta.persistence.*;
import lombok.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import static com.study.jpa.domain.ItemTest.Member.UserType.USER;
import static java.util.Collections.*;
import static org.assertj.core.api.Assertions.assertThat;

public class ItemTest {

    @Test
    @DisplayName("고객은 상품을 주문할 수 있다.")
    void custom_buy_product() {
        Member member = Member.builder()
                .id(1L)
                .name("mulgom")
                .createdDate(LocalDateTime.now())
                .build();

        Item item = Item.builder()
                .id(1L)
                .name("bread")
                .price(BigInteger.valueOf(100000L))
                .quantity(100)
                .build();


        // 회원은 상품을 주문한다.
        Order order = member.order(item);

        assertThat(order).isNotNull();
    }

    @Entity
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    class Item{
        @Id
        private Long id;
        private String name;
        private BigInteger price;
        private Integer quantity;
    }

    @Entity
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    private class Order {
        @Id
        private Long id;
        @OneToMany
        private List<Item> itemList;
        private LocalDateTime orderDate;
        private BigInteger orderPrice;
        @Enumerated(EnumType.STRING)
        private Status status;

        public boolean addItem(Item... items) {
            addAll(this.itemList, items);

            return true;
        }
        public enum Status {
            ORDER, CANCEL,
        }
    }


    @Entity
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
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
        @OneToMany
        private List<Order> orderList;

        public enum UserType {
            ADMIN, USER
        }

        public Order order(Item... items) {
            // 회원이 상품을 주문한다.
            Order order = Order.builder()
                    .id(1L)
                    .orderDate(LocalDateTime.now())
                    .build();

            // 해당 아이템을 포함한 주문이 생성되어 반환된다.
            order.addItem(items);

            // 회원의 주문 목록에 주문을 추가한다.
            this.orderList.add(order);

            // 생성 된 주문을 반환한다.
            return order;
        }
    }
}
