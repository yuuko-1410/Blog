package com.yuuko.MyBlog.response.user;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.util.Date;

@Data
public class UserInfoResponse {
    /**
     * ID
     */
    private String uid;
    /**
     * 用户名
     */
    private String username;
    /**
     * 头像链接
     */
    private String avatarUrl;
    /**
     * 手机号
     */
    private String phoneNumber;
    /**
     * 邮箱号
     */
    private String emil;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改时间
     */
    private Date createTime;
}