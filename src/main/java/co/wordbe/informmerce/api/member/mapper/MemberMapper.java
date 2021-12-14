package co.wordbe.informmerce.api.member.mapper;

import co.wordbe.informmerce.api.member.dto.MemberCreateRequestDto;
import co.wordbe.informmerce.api.member.dto.MemberCreateResponseDto;
import co.wordbe.informmerce.domain.member.entity.MemberEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberEntity toEntity(MemberCreateRequestDto requestDto);
    MemberCreateResponseDto toDto(MemberEntity memberEntity);
}
