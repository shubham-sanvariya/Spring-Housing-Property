package com.shubh.housing_property.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shubh.housing_property.request.UserRequest;
import com.shubh.housing_property.response.BaseResponse;
import com.shubh.housing_property.services.Userservice;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private Userservice userservice;

    @PostMapping("/signup")
    public ResponseEntity<BaseResponse> signup(@RequestBody UserRequest userRequest){
        BaseResponse res = userservice.signup(userRequest);
        return ResponseEntity.ok(res);
    }

    @PostMapping("/verify")
    public ResponseEntity<BaseResponse> verifyOtp(@RequestParam String email, @RequestParam String otp){
        BaseResponse res = userservice.verifyOtp(email, otp);
        return ResponseEntity.ok(res);
    }
}
