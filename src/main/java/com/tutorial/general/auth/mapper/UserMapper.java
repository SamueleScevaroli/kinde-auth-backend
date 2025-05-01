package com.tutorial.general.auth.mapper;

import com.tutorial.general.auth.dto.UserDto;
import com.tutorial.general.auth.entity.UserEntity;
import com.tutorial.general.auth.model.User;
import com.tutorial.general.mapper.MapStructConfig;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = AuthorityMapper.class,
        componentModel = "spring")
public interface UserMapper {

    User toModel(UserEntity userEntity);

    UserEntity toEntity(User user);

    UserDto toDto(User user);

}