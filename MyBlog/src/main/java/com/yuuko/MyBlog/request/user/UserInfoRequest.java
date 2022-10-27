package com.yuuko.MyBlog.request.user;

import io.swagger.models.auth.In;
import lombok.Data;


@Data
public class UserInfoRequest {
    //分页
    Integer current;
    Integer page;
    Integer disable;
    //参数
    String pram;
    String Type;
    /**
     * ID
     */
    private String uid;
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 头像链接
     */
    private String avatarUrl;

    /**
     * 账户权限
     */
    private String userAuthority;

    /**
     * 手机号
     */
    private String phoneNumber;

    /**
     * 邮箱号
     */
    private String emil;
}
