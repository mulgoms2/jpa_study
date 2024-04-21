package com.study.jpa.domain;


import jakarta.persistence.Embeddable;

@Embeddable
public record Address(String city, String street, String zipcode) {
    // 값타입은 본 엔티티가 db에서 삭제시 같이 삭제되나요?
    // 정답: 컬렉션이 아닌 단순 값타입은 엔티티의 필드가 된다. 따라서 같이 삭제된다.
}
