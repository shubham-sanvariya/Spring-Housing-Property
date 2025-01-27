package com.shubh.housing_property.managers;

import org.springframework.stereotype.Component;

import com.shubh.housing_property.dtos.UserDTO;
import com.shubh.housing_property.entities.User;
import com.shubh.housing_property.mappers.UserMapper;
import com.shubh.housing_property.repos.UserRepo;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserManager {

    private UserRepo userRepo;

    public UserDTO getByEmail(String email){
        User user = userRepo.findByEmail(email).orElse(null);

        return user != null ? UserMapper.INSTANCE.toUserDTO(user) : null;
    }

    public void save(UserDTO userDTO){
        userRepo.save(UserMapper.INSTANCE.toUser(userDTO));
    }
}