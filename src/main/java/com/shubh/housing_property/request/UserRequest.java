package com.shubh.housing_property.request;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequest {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private boolean verified;
    private String otp;
    private LocalDateTime otpGeneratedTime;
}
