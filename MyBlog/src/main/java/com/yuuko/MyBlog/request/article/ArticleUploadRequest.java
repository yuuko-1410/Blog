package com.yuuko.MyBlog.request.article;

import lombok.Data;

@Data
public class ArticleUploadRequest {
    String tid;
    String id;
    String title;
    String text;
    String[] tag;
}
