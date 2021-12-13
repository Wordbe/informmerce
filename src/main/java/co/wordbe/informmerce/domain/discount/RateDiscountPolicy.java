package co.wordbe.informmerce.domain.discount;

import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.enums.MemberGrade;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RateDiscountPolicy implements DiscountPolicy {

    private BigDecimal discountPercent = BigDecimal.valueOf(10);

    @Override
    public BigDecimal discountAmount(MemberEntity member, BigDecimal price) {
        if (member.getGrade() == MemberGrade.VIP) {
            return price.multiply(discountPercent)
                    .divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
        }
        return BigDecimal.ZERO;
    }
}
