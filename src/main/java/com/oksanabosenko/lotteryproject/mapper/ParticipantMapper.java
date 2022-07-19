package com.oksanabosenko.lotteryproject.mapper;

import com.oksanabosenko.lotteryproject.dto.ParticipantDto;
import com.oksanabosenko.lotteryproject.entity.ParticipantEntity;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Objects;

@Mapper(componentModel = "spring",
        imports = {Objects.class})
public interface ParticipantMapper {

    ParticipantMapper instance = Mappers.getMapper(ParticipantMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "winner", ignore = true)
    ParticipantEntity mapDtoToEntity(ParticipantDto dto);

    @Named(value = "mapEntityToDto")
    @Mapping(target = "winner", expression = "java( Objects.nonNull(entity.getWinner()) ? true : false )")
    @Mapping(target = "money", source = "entity.winner.lotteryMoney", defaultValue = "0")
    ParticipantDto mapEntityToDto(ParticipantEntity entity);

    @IterableMapping(qualifiedByName = "mapEntityToDto")
    List<ParticipantDto> mapEntityListToDtoList(List<ParticipantEntity> entities);


}
