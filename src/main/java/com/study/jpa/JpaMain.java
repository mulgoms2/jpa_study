package com.study.jpa;

import com.study.jpa.domain.Member;
import com.study.jpa.domain.Minwoo;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        // 단 하나만 생성해야 한다. 어플리케이션 전체에서 공유
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_study");
        // 단위작업(트랜잭션) 당 하나 만들고 클로즈 하는 듯 하다. 요청 -> 응답 사이클당 하나가 생성되고 사라진다.
        // 쓰레드 간의 공유자원이 아닌 전적으로 한 유저의 요청에 대해 사용되고 버려져야 하는 자원이다.
        EntityManager entityManager = emf.createEntityManager();
        EntityTransaction tx = entityManager.getTransaction();
        tx.begin();

        try {
            Member member = Member.builder()
                    .name("mulgom")
                    .userType(Member.UserType.USER)
                    .age(10)
                    .build();
//
            entityManager.persist(member);

//            System.out.println(getAsList(entityManager, Member.class));

            // 멤버객체의 id를 autoincrement 로 바꿔보자

            // 트랜잭션이 일어나기 전에 더티체킹을 해서 db 업데이트(동기화)가 진행된다.
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
//            엔티티 매니저를 닫아주는 것이 중요하다. db 커넥션을 풀에 반환시키는 것으로 보인다.
            entityManager.close();
        }

        emf.close();
    }

    private static List<?> getAsList(EntityManager em, Class<?> clazz) {
        return em.createQuery("select m from Member m", clazz)
                .getResultList();
    }
}
