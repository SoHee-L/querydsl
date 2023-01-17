package study.querydsl.entitiy;

import lombok.*;
import javax.persistence.*;
@Entity
@Getter @Setter //@Setter: 실무에서 가급적 Setter는 사용하지 않기
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "username", "age"})
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;
    @ManyToOne(fetch = FetchType.LAZY) //ManyToOne에서는 항상 fetch를 LAZY로 해줘야함.
    @JoinColumn(name = "team_id") // 외래키 컬럼 이름이 됨.
    private Team team;
    public Member(String username) {
        this(username, 0);
    }
    public Member(String username, int age) {
        this(username, age, null);
    }
    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if (team != null) {
            changeTeam(team);
        }
    }
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}