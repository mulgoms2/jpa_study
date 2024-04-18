package com.study.jpa.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
class Order {
    @Id
    private Long id;
//    @OneToMany
//    private final List<Item> itemList = new ArrayList<>();
    private LocalDateTime orderDate;
    private BigInteger orderPrice;
    @Enumerated(EnumType.STRING)
    private Status status;

//    public boolean addItem(Item... items) {
//        Collections.addAll(this.itemList, items);
//        this.itemList.addAll(Arrays.asList(items));
//        for (Item item : items) {
//            this.itemList.add(item);
//        }
//
//        return true;
//    }

    public enum Status {
        ORDER, CANCEL
    }
}
