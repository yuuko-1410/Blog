package com.yuuko.MyBlog.request.article;


import lombok.Data;


@Data
public class ArticleInfoRequest {
    private String tid;
    private String title;
    private String context;
    private Integer disable;
    private String imgUrl;
}



