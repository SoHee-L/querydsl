package study.querydsl.entitiy;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@SpringBootTest
@Transactional
public class MemberTest {
    @PersistenceContext
    EntityManager em;
    @Test

    public void testEntity() {
        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        em.persist(teamA);
        em.persist(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 20, teamA);
        Member member3 = new Member("member3", 30, teamB);
        Member member4 = new Member("member4", 40, teamB);
        em.persist(member1);
        em.persist(member2);
        em.persist(member3);
        em.persist(member4);
        //초기화
        em.flush(); //영속성 컨텍스트에 있는 오브젝트같은 실제 쿼리들을 만들어서 DB에 날림
        em.clear(); //영속성 컨텍스트를 완전히 초기화해서 캐시가 다날라가서 그다음에 쿼리를 날리면 깔끔하게 나온다.
        //확인
        List<Member> members = em.createQuery("select m from Member m",
                        Member.class)
                .getResultList();
        for (Member member : members) {
            //원래 테스트 짤때는 이렇게 sysout으로 찍으면안되고 assert로 검증해야됨. 자동화 되었으니까
            System.out.println("member=" + member);
            System.out.println("-> member.team=" + member.getTeam());
        }
    }
}
