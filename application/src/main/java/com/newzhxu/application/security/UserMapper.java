package com.newzhxu.application.security;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "authorities", ignore = true)
    // 核心：忽略 authorities 字段
    @Mapping(target = "id", ignore = true)      // 更新时跳过 ID
    @Mapping(target = "version", ignore = true) // 更新时跳过 Version
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserFromMyUser(MyUser source, @MappingTarget MyUser target);
}
