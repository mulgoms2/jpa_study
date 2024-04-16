package com.study.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Member {
    @Id
    private Long id;
    private String name;
}
