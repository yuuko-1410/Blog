package com.yuuko.MyBlog.response.article;

import com.yuuko.MyBlog.domain.BlogArticle;
import com.yuuko.MyBlog.domain.vo.ArticleUser;
import lombok.Data;

import java.util.List;

@Data
public class ArticleListResponse {
    //分页
    private int current;
    private int page;
    private int size;
    private List<ArticleUser> blogArticles;
}
