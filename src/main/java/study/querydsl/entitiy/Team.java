package study.querydsl.entitiy;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Getter @Setter
//JPA는 기본생성자가 있어야됨 JPA는 기본생성자는 PROTECTED까지는 허용해줌.
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//ToString을 자동으로 만들어줌. 근데 조심해야될건 Team이 들어가면 안됨 무한루프를 타버려서 오류가 나기때문
//가급적이면 본인이 소유한 field정도만 선언하고 toString에서는 연관관계에서 손안대는게 좋음.
@ToString(of = {"id", "name"})
public class Team {
    @Id @GeneratedValue
    @Column(name = "team_id")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "team") //양방향 연관관계기 때문에 OneToMany가 반대쪽에 하나 있어야됨
    //연관관계 주인이 아니라 외래키 값을 업데이트 하지 않음.
    private List<Member> members = new ArrayList<>();
    public Team(String name) {
        this.name = name;
    }
}