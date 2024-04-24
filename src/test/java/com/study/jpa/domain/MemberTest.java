package com.study.jpa.domain;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class MemberTest {
    Member member;
    Item item;
    Item item2;
    static final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("jpa_test");
    EntityManager entityManager;
    private EntityTransaction transaction;

    @BeforeEach
    void setUp() {
        String description = "테스트를 위해 만들어진 회원 객체입니다.";

        member = Member.builder()
                .name("mulgom")
                .age(20)
                .createdDate(LocalDateTime.now())
                .modifiedDate(LocalDateTime.now())
                .description(description)
                .build();


        item = Item.builder()
                .price(BigInteger.valueOf(1000000L))
                .quantity(1000) // 상품 초기 갯수
                .createdAt(LocalDateTime.now())
                .build();


        item2 = Item.builder()
                .price(BigInteger.valueOf(30000L))
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
        // 왜 주문에 멤버의 아이디가 저장되지 않았을까?
        // 주문과 멤버는 어떤 관계?
        // 멤버가 주문 목록을 갖는 관계
        // 주문이 연관관계의 주인?

        // 회원이 상품을 주문한다.
        entityManager.persist(member);
        entityManager.persist(item);
        entityManager.persist(item2);

//        Order order = member.order(item, item2);
//        order.setMember(member);

        // 주문이 정상적으로 db에 반영된다.
//        entityManager.persist(order);

        // db에서 주문이 조회 가능하다.
//        Order findOrder = entityManager.find(Order.class, order.getId());

//        assertThat(order).isEqualTo(findOrder);
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

    @Test
    @DisplayName("엔티티 삭제시 값타입이 db에서 같이 사라질까?")
    void voDeleteTest() {
        member.setAddress(new Address("Bucheon", "yangji", "105"));

        entityManager.persist(member);

        entityManager.flush();
        entityManager.clear();

        Member findMember = entityManager.find(Member.class, member.getId());
        // 단일한 값 타입은 엔티티의 필드로 포함된다. 따라서 delete 쿼리가 한번 실행된다.
        entityManager.remove(findMember);
    }

    @Test
    @DisplayName("컬렉션 값타입의 생명주기는 어떻게 관리될까?")
    void elementCollectionDelete() {
        List<Address> addresses = new ArrayList<>();

        addresses.add(new Address("bucheon", "yangji", "1022"));
        addresses.add(new Address("songdo", "yangji", "1022"));
        addresses.add(new Address("incheon", "yangji", "1022"));

//        member.setAddresses(addresses);

        entityManager.persist(member);

        entityManager.flush();
        entityManager.clear();

        Member findMember = entityManager.find(Member.class, member.getId());
        // 주소는 엔티티가 아니어서 식별자를 갖지 않는다. => 조회할 수 없다, 또한 영속성 컨텍스트에서 별도로 관리되지 않는 객체이다.
//        Address address = entityManager.find(Address.class, 키가없다)

        // 멤버 엔티티의 값타입 컬렉션을 삭제하기위한 delete 쿼리가 한번 더 나간다.
        entityManager.remove(findMember);
    }

    @Test
    @DisplayName("Entity에 공통속성 부여하기 @MappedSuperClass")
    void superClass() {
        // 멤버의 등록 및 수정일 필드가 다른 엔티티에서도 중복된다. 따라서 해당 필드를 상속을 통해 부여해보자.

        member.setUserType(Member.UserType.USER);
        entityManager.persist(member);
        System.out.println("-------------쓰기지연이 일어나지 않는다.-----------");

//        entityManager.flush();
//        entityManager.clear();


//        Member findMember = entityManager.find(Member.class, member.getId());
//
//        System.out.println(findMember);
    }
}