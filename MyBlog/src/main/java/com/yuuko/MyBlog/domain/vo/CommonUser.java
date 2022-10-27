package com.yuuko.MyBlog.domain.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CommonUser {
    private String cid;
    private String tid;
    private String uid;
    private String fromCid;
    private String context;
    private Date updateTime;
    private Date createTime;
    //联表
    private String username;
    private String avatarUrl;
}
