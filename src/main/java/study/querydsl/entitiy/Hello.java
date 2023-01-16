package study.querydsl.entitiy;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
public class Hello {

    //Querydsl의 큐타입을 확인하기 위해 단순히 식별자만 만듬
    @Id @GeneratedValue
    private Long id;
}
