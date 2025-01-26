package com.shubh.housing_property.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LoginResponse extends BaseResponse{
    private String token;
}
