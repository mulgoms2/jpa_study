package com.study.jpa.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Minwoo {
    private String name;
    private Integer age;
    @Id
    private Long id;

    @Builder
    private Minwoo(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public static Minwoo getInstance(String name, Integer age) {
        return Minwoo.builder()
                .name("me")
                .age(2)
                .build();
    }

}