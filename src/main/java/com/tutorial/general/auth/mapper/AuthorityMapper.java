package com.tutorial.general.auth.mapper;

import com.tutorial.general.auth.entity.AuthorityEntity;
import com.tutorial.general.mapper.MapStructConfig;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapStructConfig.class,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        componentModel = "spring")
public interface AuthorityMapper {

    String toString(AuthorityEntity authorityEntity);

    @Mapping(source = ".", target = "name")
    AuthorityEntity toEntity(String authorityName);
}