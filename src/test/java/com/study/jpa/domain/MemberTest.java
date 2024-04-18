package com.study.jpa.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.math.BigInteger;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


class MemberTest {
    Member member;
    Item item;
    static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_test");
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
    void close() {
        transaction.commit();
        entityManager.close();
    }

    @AfterAll
    static void closeAll() {
        entityManagerFactory.close();
    }

    @Test
    @DisplayName("회원은 상품을 주문할 수 있다.")
    void test() {
        // 회원이 상품을 주문한다.
        Order order = member.order(item);

        // 주문이 정상적으로 db 에 반영된다.
        entityManager.persist(order);

        // db에서 주문이 조회 가능하다.
        Order findOrder = entityManager.find(Order.class, order.getId());

        assertThat(order).isEqualTo(findOrder);
    }

    @Test
    @DisplayName("멤버와 팀 연관관계 설정하고 저장하기")
    void setEngage() {

        Team team = Team.builder()
                .name("football")
                .build();

        team.getMembers()
                .add(member);
        entityManager.persist(team);

        member.setTeam(team);
        entityManager.persist(member);

//        entityManager.flush();
//        entityManager.clear();

        // 1차 캐시에서 조회시 양방향 연관관계가 정상적으로 매핑되지 않는다.
        // 양방향 매핑시 양쪽 다 직접 객체의 관계를 설정해주어야 한다.
        // 연관관계의 주인이 아닌 매핑은 읽기 전용이라 db의 반영과는 상관이 없으나 1차 캐시에서의 조회시점에는 영향을 줄 수 있다.
        Team team1 = entityManager.find(Team.class, team.getId());

        for (Member team1Member : team1.getMembers()) {
            System.out.println(team1Member);
        }
    }
}