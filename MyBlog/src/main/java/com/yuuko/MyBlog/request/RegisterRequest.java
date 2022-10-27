package com.yuuko.MyBlog.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String emil;
    private String code;
    private String captcha;
}
