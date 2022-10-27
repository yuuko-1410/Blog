package com.yuuko.MyBlog.service;

import com.yuuko.MyBlog.domain.BlogArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yuuko.MyBlog.request.article.ArticleInfoRequest;
import com.yuuko.MyBlog.request.article.ArticleSelectRequest;
import com.yuuko.MyBlog.request.article.ArticleUploadRequest;
import com.yuuko.MyBlog.response.article.ArticleInfoResponse;
import com.yuuko.MyBlog.response.article.ArticleListResponse;

/**
* @author shibi
* @description 针对表【blog_article】的数据库操作Service
* @createDate 2022-10-18 17:17:14
*/
public interface BlogArticleService extends IService<BlogArticle> {
    String uploadArticle(ArticleUploadRequest articleUploadRequest);
    String updateArticle(ArticleInfoRequest articleInfoRequest);
    String removeArticleById();
    ArticleInfoResponse getArticleDetailsById(String tid);
    ArticleListResponse getArticleDetailsList(ArticleSelectRequest articleSelectRequest);

}
