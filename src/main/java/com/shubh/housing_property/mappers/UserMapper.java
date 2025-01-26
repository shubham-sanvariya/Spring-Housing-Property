package com.shubh.housing_property.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.shubh.housing_property.dtos.UserDTO;
import com.shubh.housing_property.entities.User;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toUser(UserDTO userDTO);
    
}
