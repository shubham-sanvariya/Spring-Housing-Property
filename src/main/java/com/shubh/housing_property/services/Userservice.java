package com.shubh.housing_property.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shubh.housing_property.dtos.UserDTO;
import com.shubh.housing_property.managers.UserManager;
import com.shubh.housing_property.mappers.UserMapper;
import com.shubh.housing_property.request.UserRequest;
import com.shubh.housing_property.utils.EmailUtility;
import com.shubh.housing_property.utils.JwtUtility;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Userservice {
    
    private UserManager userManager;

    private EmailUtility emailUtility;

    private PasswordEncoder passwordEncoder;

    private JwtUtility jwtUtility;

    public String signup(UserRequest userRequest){
        UserDTO userDTO = userManager.getByEmail(userRequest.getEmail());

        if (userDTO != null) {
            throw new IllegalArgumentException("Email is already registered.");
        }

        userDTO = UserMapper.INSTANCE.toUserDTO(userRequest);
        userDTO.setPassword(passwordEncoder.encode(userRequest.getPassword()));

        String otp = String.valueOf(new Random().nextInt(9000) + 1000);
        userDTO.setOtp(otp);
        userDTO.setOtpGeneratedTime(LocalDateTime.now());

        userManager.save(userDTO);

        emailUtility.sendEmail(userDTO.getEmail(), "Email Verification", "Your OTP is: " + otp);

        return "Signup successfull Please verify your email.";
    }
}
