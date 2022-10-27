package com.yuuko.MyBlog.request.article;

import lombok.Data;

@Data
public class ArticleSelectRequest {
    //分页
    int current;
    int page;
    String type;
    String pram;
}
