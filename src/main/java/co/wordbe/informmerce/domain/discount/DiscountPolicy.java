package co.wordbe.informmerce.domain.discount;

import co.wordbe.informmerce.domain.member.entity.MemberEntity;

import java.math.BigDecimal;

public interface DiscountPolicy {

    BigDecimal discountAmount(MemberEntity member, BigDecimal price);
}
