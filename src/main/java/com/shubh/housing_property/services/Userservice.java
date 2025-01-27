package com.shubh.housing_property.services;

import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shubh.housing_property.dtos.UserDTO;
import com.shubh.housing_property.managers.UserManager;
import com.shubh.housing_property.mappers.UserMapper;
import com.shubh.housing_property.request.UserRequest;
import com.shubh.housing_property.response.BaseResponse;
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

    public BaseResponse signup(UserRequest userRequest) {
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

        return new BaseResponse(200, "Signup successful! Please verify your email.");
    }

    public BaseResponse verifyOtp(String email, String otp) {
        UserDTO userDTO = userManager.getByEmail(email);

        if (userDTO == null) {
            throw new UsernameNotFoundException("User not found");
        }

        if (userDTO.getOtp().equals(otp)) {
            if (userDTO.getOtpGeneratedTime().isBefore(LocalDateTime.now().minusMinutes(10))) {
                throw new IllegalArgumentException("OTP has expired.");
            }

            userDTO.setVerified(true);
            userDTO.setOtp(null);
            userDTO.setOtpGeneratedTime(null);

            userManager.save(userDTO);

            return new BaseResponse(200, "Email verified successfully!");
        }else{
            throw new IllegalArgumentException("Invalid OTP.");
        }
    }
}
