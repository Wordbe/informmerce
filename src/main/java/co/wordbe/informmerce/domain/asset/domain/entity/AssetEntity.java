package co.wordbe.informmerce.domain.asset.domain.entity;

import co.wordbe.informmerce.domain.asset.domain.enums.IndustryType;
import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "asset")
public class AssetEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private IndustryType industry;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private MemberEntity member;

    @Column(name = "member_id", insertable = false, updatable = false)
    private Long memberId;

    @Builder
    public AssetEntity(Long id, IndustryType industry, MemberEntity member) {
        this.id = id;
        this.industry = industry;
        this.member = member;
    }
}
