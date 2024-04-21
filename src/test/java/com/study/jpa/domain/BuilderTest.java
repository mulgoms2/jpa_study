package com.study.jpa.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class BuilderTest {

    @Test
    @DisplayName("빌더 클래스 만들기 연습")
    void abstractNestedClass() {
        Minwoo minwoo = Minwoo.builder()
                .age(10)
                .name("mulgom")
                .build();

        Children children = (Children) Children.builder()
                .age(10)
                .name("hehe")
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .build();
    }
}
