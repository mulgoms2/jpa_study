package com.study.jpa.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "teams")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @OneToMany(mappedBy = "team", cascade = CascadeType.PERSIST)
    private final List<Member> members = new ArrayList<>();

    @Builder
    public Team(String name) {
        this.name = name;
    }
}