package com.kenpxrk.security.mapper;

import com.kenpxrk.security.dto.RegisterFormDto;
import com.kenpxrk.security.dto.UserUpdateDto;
import com.kenpxrk.security.model.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserEntity registerFormToUserEntity(RegisterFormDto registerFormDto);

    @Mapping(target = "username", source = "username", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "email", source = "email", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "age", source = "age", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserEntityFromDto(UserUpdateDto userUpdateDto, @MappingTarget UserEntity entity);

}
