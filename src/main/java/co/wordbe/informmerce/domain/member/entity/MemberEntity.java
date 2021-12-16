package co.wordbe.informmerce.domain.member.entity;

import co.wordbe.informmerce.domain.member.enums.MemberAuthProvider;
import co.wordbe.informmerce.domain.member.enums.MemberGrade;
import co.wordbe.informmerce.domain.member.enums.MemberRoleType;
import io.jsonwebtoken.io.Encoders;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.apache.commons.lang3.RandomStringUtils;
import org.bouncycastle.util.encoders.Encoder;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "member",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"email", "provider"})})
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
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

    @Column
    private String secretKey;
    @Column
    private LocalDateTime secretKeyExpiredAt;

    @Column
    private LocalDateTime lastLoginAt;

    @Builder
    public MemberEntity(Long id, String email, String password, String name, MemberGrade grade, MemberRoleType role, MemberAuthProvider provider, String secretKey, LocalDateTime secretKeyExpiredAt, LocalDateTime lastLoginAt) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.grade = grade;
        this.role = role;
        this.provider = provider;
        this.secretKey = secretKey;
        this.secretKeyExpiredAt = secretKeyExpiredAt;
        this.lastLoginAt = lastLoginAt;
    }

    public void init() {
        this.grade = MemberGrade.BRONZE;
        this.role = MemberRoleType.USER;
        this.provider = MemberAuthProvider.INFORMMERCE;
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateLastLoginAt(LocalDateTime lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public void updateSecretKey(int length) {
        this.secretKey = RandomStringUtils.randomAlphanumeric(length);
    }

    public void updateSecretKeyExpiredAt(LocalDateTime secretKeyExpiredAt) {
        this.secretKeyExpiredAt = secretKeyExpiredAt;
    }
}
