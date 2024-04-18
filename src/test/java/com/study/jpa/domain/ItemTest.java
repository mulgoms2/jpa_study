package com.study.jpa.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemTest {

    @Test
    @DisplayName("고객은 상품을 주문할 수 있다.")
    void custom_buy_product() {
        Member member = Member.builder()
                .name("mulgom")
                .createdDate(LocalDateTime.now())
                .build();

        Item item = Item.builder()
                .name("bread")
                .price(BigInteger.valueOf(100000L))
                .quantity(100)
                .build();


        // 회원은 상품을 주문한다.
        Order order = member.order(item);

        assertThat(order).isNotNull();
    }


}
