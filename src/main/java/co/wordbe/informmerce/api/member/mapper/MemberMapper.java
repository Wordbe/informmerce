package co.wordbe.informmerce.api.member.mapper;

import co.wordbe.informmerce.api.common.mapper.CommonMapperConfig;
import co.wordbe.informmerce.api.member.dto.MemberCreateRequestDto;
import co.wordbe.informmerce.api.member.dto.MemberCreateResponseDto;
import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import co.wordbe.informmerce.domain.member.model.MemberAttribute;
import org.mapstruct.Mapper;

@Mapper(config = CommonMapperConfig.class)
public interface MemberMapper {
    MemberEntity toEntity(MemberCreateRequestDto requestDto);
    MemberCreateResponseDto toDto(MemberEntity memberEntity);
    MemberAttribute toAttribute(MemberEntity memberEntity);
}
