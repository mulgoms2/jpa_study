package com.study.jpa.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.hibernate.Transaction;
import org.hibernate.jpa.boot.spi.EntityManagerFactoryBuilder;
import org.junit.jupiter.api.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


class MemberTest {
    Member member;
    Item item;
    final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_test");
    EntityManager entityManager;
    private EntityTransaction transaction;

    @BeforeEach
    void setUp() {
        String description = "테스트를 위해 만들어진 회원 객체입니다.";

        member = Member.builder()
                .name("mulgom")
                .createdDate(LocalDateTime.now())
                .description(description)
                .build();


        item = Item.builder()
                .price(BigInteger.valueOf(1000000L))
                .quantity(1000) // 상품 초기 갯수
                .createdAt(LocalDateTime.now())
                .build();

        entityManager = entityManagerFactory.createEntityManager();
        transaction = entityManager.getTransaction();
        transaction.begin();
    }

    @AfterEach
    void close(){
        transaction.commit();
        entityManager.close();
    }

    @Test
    @DisplayName("회원은 상품을 주문할 수 있다.")
    void test() {
        // 회원이 상품을 주문한다.
//        Order order = member.order(item);

        // 주문이 정상적으로 db 에 반영된다.
//        entityManager.persist(order);

        // db에서 주문이 조회 가능하다.
//        Order findOrder = entityManager.find(Order.class, order.getId());

//        assertThat(order).isEqualTo(findOrder);
    }

    @Test
    @DisplayName("멤버와 팀 연관관계 설정하고 저장하기")
    void setEngage() {

        Team team = new Team(1L, "football");
//        entityManager.persist(team);
        member.setTeam(team);

        entityManager.persist(member);

        String name = """
                %s
                dfdfa
                
                dfdafa
                dfadf
                
                daf
                """.formatted("hello");

        System.out.println(name);

    }
}