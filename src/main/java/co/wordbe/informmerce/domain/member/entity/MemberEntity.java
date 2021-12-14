package co.wordbe.informmerce.domain.member.entity;

import co.wordbe.informmerce.domain.member.enums.MemberGrade;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member")
public class MemberEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private MemberGrade grade;

    @Builder
    public MemberEntity(Long id, String name, String email, String password, MemberGrade grade) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.grade = grade;
    }
}
