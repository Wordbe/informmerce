package co.wordbe.informmerce.domain.member.entity;

import co.wordbe.informmerce.domain.member.enums.MemberAuthProvider;
import co.wordbe.informmerce.domain.member.enums.MemberGrade;
import co.wordbe.informmerce.domain.member.enums.MemberRoleType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member",
       uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "provider"})})
public class MemberEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberGrade grade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRoleType role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberAuthProvider provider;

    @Builder
    public MemberEntity(Long id, String email, String password, String name, MemberGrade grade, MemberRoleType role, MemberAuthProvider provider) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.role = role;
        this.provider = provider;
    }

    public void init() {
        this.grade = MemberGrade.BRONZE;
        this.role = MemberRoleType.USER;
        this.provider = MemberAuthProvider.INFORMMERCE;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
