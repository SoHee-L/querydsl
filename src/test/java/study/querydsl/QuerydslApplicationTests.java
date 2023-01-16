package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import study.querydsl.entitiy.Hello;
import study.querydsl.entitiy.QHello;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@SpringBootTest
@Transactional
@Commit //테스트에 @Transactional이 있으면 다 롤백을 해버림 그렇기 때문에 @Commit을 넣어준다.
class QuerydslApplicationTests {

	//@PersistenceContext // 자바 표준스펙에서 써야됨
	@Autowired //Spring 최신버전. @PersistenceContext 과 똑같은 기능으로 동작.
	EntityManager em;

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);

		//최신버전에선 Querydsl 최근버전에선 JPAQueryFactory를 사용하도록 권장한다고함.
		JPAQueryFactory query = new JPAQueryFactory(em);
		//QHello qHello = new QHello("h");
		QHello qHello = QHello.hello; //QHello 들어가보면 static으로 자기자신을 간단하게 만들어놓은게 있음.

		Hello result = query
				.selectFrom(qHello)//Hello Entity를 넣는게 아닌 쿼리와 관련된거면 query와 관련된거는 다 큐타입을 넣음.
				.fetchOne();

		Assertions.assertThat(result).isEqualTo(hello);
		//lombok 세팅 확인 test
		Assertions.assertThat(result.getId()).isEqualTo(hello.getId());

	}

}
