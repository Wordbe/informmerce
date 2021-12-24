package co.wordbe.informmerce.domain.discount;

import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.enums.MemberGrade;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.Assertions.assertThat;

class RateDiscountPolicyTest {
    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    void VIP는_10퍼센트_할인이_적용된다() {
        MemberEntity member = MemberEntity.builder()
                .id(1L)
                .name("memberVIP")
                .grade(MemberGrade.VIP)
                .build();

        BigDecimal discount = discountPolicy.discountAmount(member, BigDecimal.valueOf(10000));

        assertThat(discount).isEqualTo(BigDecimal.valueOf(1000).setScale(2, RoundingMode.HALF_UP));
    }

    @Test
    void VIP가_아니면_할인이_적용되지_않는다() {
        MemberEntity member = MemberEntity.builder()
                .id(2L)
                .name("memberBronze")
                .grade(MemberGrade.BRONZE)
                .build();

        BigDecimal discount = discountPolicy.discountAmount(member, BigDecimal.valueOf(10000));

        assertThat(discount).isEqualTo(BigDecimal.ZERO);
    }
}