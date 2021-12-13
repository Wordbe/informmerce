package co.wordbe.informmerce.domain.discount;

import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.enums.MemberGrade;

import java.math.BigDecimal;

public class FixDiscountPolicy implements DiscountPolicy {

    private static final BigDecimal FixedDiscountAmount = BigDecimal.valueOf(1000);

    @Override
    public BigDecimal discountAmount(MemberEntity member, BigDecimal price) {
        if (member.getGrade() == MemberGrade.VIP) {
            return FixedDiscountAmount;
        }
        return BigDecimal.ZERO;
    }
}
