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

    @Mapping(source = "addressStreet", target = "userAddress.street")
    @Mapping(source = "addressCity", target = "userAddress.city")
    @Mapping(source = "addressZipCode", target = "userAddress.zipCode")
    @Mapping(source = "addressCountry", target = "userAddress.country")
    User toModel(UserEntity userEntity);

    @Mapping(source = "userAddress.street", target = "addressStreet")
    @Mapping(source = "userAddress.city", target = "addressCity")
    @Mapping(source = "userAddress.zipCode", target = "addressZipCode")
    @Mapping(source = "userAddress.country", target = "addressCountry")
    UserEntity toEntity(User user);

    UserDto toDto(User user);

}