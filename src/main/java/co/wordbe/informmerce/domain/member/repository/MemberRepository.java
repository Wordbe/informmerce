package co.wordbe.informmerce.domain.member.repository;

import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}