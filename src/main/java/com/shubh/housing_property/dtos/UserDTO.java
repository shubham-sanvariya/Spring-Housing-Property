package com.shubh.housing_property.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private boolean verified;
    private String otp;
    private LocalDateTime otpGeneratedTime;
}
