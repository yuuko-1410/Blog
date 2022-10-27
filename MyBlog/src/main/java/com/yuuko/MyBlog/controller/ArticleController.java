package com.yuuko.MyBlog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.yuuko.MyBlog.common.restful.R;
import com.yuuko.MyBlog.mapper.BlogArticleMapper;
import com.yuuko.MyBlog.request.article.ArticleInfoRequest;
import com.yuuko.MyBlog.request.article.ArticleSelectRequest;
import com.yuuko.MyBlog.request.article.ArticleUploadRequest;
import com.yuuko.MyBlog.response.article.ArticleInfoResponse;
import com.yuuko.MyBlog.response.article.ArticleListResponse;


import com.yuuko.MyBlog.service.BlogArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin("*")
@Api(value = "文章接口", tags = {"文章接口"})
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    BlogArticleService blogArticleService;

    @Autowired
    BlogArticleMapper blogArticleMapper;

    /**
     * 上传文章
     *
     * @param articleUploadRequest 文章上传请求
     * @return {@link R}
     */
    @ApiOperation("上传文章&修改文章")
    @SaCheckLogin
    @PostMapping("/uploadFile")
    R uploadArticle(@RequestBody ArticleUploadRequest articleUploadRequest){
        String msg = blogArticleService.uploadArticle(articleUploadRequest);
        return R.success(msg);
    }

    @ApiOperation("获取文章列表")
    @PostMapping("/getArticleDetailsList")
    R getArticleDetailsList(@RequestBody ArticleSelectRequest articleSelectRequest){
        ArticleListResponse detailsList = blogArticleService.getArticleDetailsList(articleSelectRequest);
        return R.success(detailsList);
    }

    @ApiOperation("获取文章信息")
    @GetMapping("/getArticleDetailsById")
    R getArticleDetailsById(@Param("tid") String tid){
        ArticleInfoResponse details = blogArticleService.getArticleDetailsById(tid);
        return R.success(details);
    }

    @SaCheckLogin
    @ApiOperation("更新文章信息")
    @PostMapping("/updateArticle")
    R updateArticle(@RequestBody ArticleInfoRequest articleInfoRequest){
        String msg = blogArticleService.updateArticle(articleInfoRequest);
        return R.success(msg);
    }
}
