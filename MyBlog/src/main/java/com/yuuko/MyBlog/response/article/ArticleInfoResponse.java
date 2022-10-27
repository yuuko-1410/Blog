package com.yuuko.MyBlog.response.article;

import lombok.Data;

import java.util.Date;

@Data
public class ArticleInfoResponse {
    private String tid;
    private String uid;
    private String title;
    private String context;
    private Integer likeNum;
    private Integer watchNum;
    private Date createTime;
    private Date updateTime;
    private Integer disable;
    private String imgUrl;
    private String address;//作者
    private String avatarUrl;//头像
}