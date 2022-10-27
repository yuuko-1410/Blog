package com.yuuko.MyBlog.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.yuuko.MyBlog.common.utils.RandomUtils;
import com.yuuko.MyBlog.domain.BlogArticle;
import com.yuuko.MyBlog.domain.BlogUser;
import com.yuuko.MyBlog.domain.vo.ArticleUser;
import com.yuuko.MyBlog.mapper.BlogUserMapper;
import com.yuuko.MyBlog.request.article.ArticleInfoRequest;
import com.yuuko.MyBlog.request.article.ArticleSelectRequest;
import com.yuuko.MyBlog.request.article.ArticleUploadRequest;
import com.yuuko.MyBlog.response.article.ArticleInfoResponse;
import com.yuuko.MyBlog.response.article.ArticleListResponse;
import com.yuuko.MyBlog.service.BlogArticleService;
import com.yuuko.MyBlog.mapper.BlogArticleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author shibi
* @description 针对表【blog_article】的数据库操作Service实现
* @createDate 2022-10-18 17:17:14
*/
@Service
public class  BlogArticleServiceImpl extends ServiceImpl<BlogArticleMapper, BlogArticle> implements BlogArticleService {

    @Autowired
    BlogArticleMapper blogArticleMapper;
    @Autowired
    BlogUserMapper blogUserMapper;
    @Override
    public String uploadArticle(ArticleUploadRequest articleUploadRequest) {
        BlogArticle blogArticle = new BlogArticle();
        blogArticle.setContext(articleUploadRequest.getText());
        blogArticle.setTitle(articleUploadRequest.getTitle());
        blogArticle.setUid(String.valueOf(StpUtil.getLoginId()));
        blogArticle.setDisable(2);//默认待审核
        blogArticle.setLikeNum(0);
        blogArticle.setWatchNum(0);
        blogArticle.setImgUrl(RandomUtils.getRandomImageUrl());
        //修改文章
        if(!"".equals(articleUploadRequest.getTid())){
            System.out.println("进来了");
            QueryWrapper<BlogArticle> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("tid",articleUploadRequest.getTid());
            BlogArticle article = blogArticleMapper.selectOne(queryWrapper);
            article.setContext(articleUploadRequest.getText());
            article.setTitle(articleUploadRequest.getTitle());
            blogArticleMapper.update(article,queryWrapper);
            return "更新成功";
        }else{
            //插入新文章
            blogArticleMapper.insert(blogArticle);
            return "保存成功";
        }
    }

    @Override
    public String updateArticle(ArticleInfoRequest articleInfoRequest) {
        QueryWrapper<BlogArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tid",articleInfoRequest.getTid());
        BlogArticle blogArticle = blogArticleMapper.selectOne(queryWrapper);

        if(articleInfoRequest.getContext() != null){
            blogArticle.setContext(articleInfoRequest.getContext());
        }
        if(articleInfoRequest.getDisable() != null){
            blogArticle.setDisable(articleInfoRequest.getDisable());
        }
        if(articleInfoRequest.getTitle() != null){
            blogArticle.setTitle(articleInfoRequest.getTitle());
        }
        if(articleInfoRequest.getImgUrl() != null){
            blogArticle.setImgUrl(articleInfoRequest.getImgUrl());
        }

        blogArticleMapper.update(blogArticle,queryWrapper);
        return "操作成功";
    }

    @Override
    public String removeArticleById() {
        return null;
    }

    @Override
    public ArticleInfoResponse getArticleDetailsById(String tid) {

        QueryWrapper<BlogArticle> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tid",tid);
        BlogArticle blogArticle = blogArticleMapper.selectOne(queryWrapper);

        ArticleInfoResponse articleInfoResponse = new ArticleInfoResponse();
        BeanUtils.copyProperties(blogArticle,articleInfoResponse);
        //获取文章作者
        BlogUser blogUser = blogUserMapper.selectById(blogArticle.getUid());
        articleInfoResponse.setAddress(blogUser.getUsername());
        articleInfoResponse.setAvatarUrl(blogUser.getAvatarUrl());
        //新增评论字段
        return articleInfoResponse;
    }

    @Override
    public ArticleListResponse getArticleDetailsList(ArticleSelectRequest articleSelectRequest) {
        //获取查询的方式
        String operateType = articleSelectRequest.getType();
        MPJLambdaWrapper<BlogArticle> blogArticleWrapper = new MPJLambdaWrapper<BlogArticle>()
                .selectAll(BlogArticle.class)
                .select(BlogUser::getUsername)
                .leftJoin(BlogUser.class, BlogUser::getUid, BlogArticle::getUid)
                .like(BlogArticle::getTitle, "%" + articleSelectRequest.getPram() + "%")
                .eq(BlogArticle::getUid, BlogUser::getUid)
                .orderByDesc(BlogArticle::getUpdateTime);
        switch (operateType){
            case "state_check"://审核中
                blogArticleWrapper.eq(BlogArticle::getDisable,2);
                break;
            case "state_disable"://未通过
                blogArticleWrapper.eq(BlogArticle::getDisable,3);
                break;
            case "state_del"://已删除
                blogArticleWrapper.eq(BlogArticle::getDisable,0);
                break;
            default://可用
                blogArticleWrapper.eq(BlogArticle::getDisable,1);
                break;
        }

        IPage<ArticleUser> articlePage = blogArticleMapper.selectJoinPage(
                new Page<>(articleSelectRequest.getCurrent(), articleSelectRequest.getPage()), ArticleUser.class, blogArticleWrapper);

        List<ArticleUser> articleList = articlePage.getRecords();
        articleList.forEach((blogArticle -> {
            String temp = blogArticle.getContext();
            if (temp.length() >= 300){
                blogArticle.setContext(temp.substring(0,300));
            }
        }));
        ArticleListResponse articleListResponse = new ArticleListResponse();
        articleListResponse.setBlogArticles(articleList);
        articleListResponse.setCurrent(articleSelectRequest.getCurrent());
        articleListResponse.setPage(articleSelectRequest.getPage());
        articleListResponse.setSize((int) articlePage.getTotal());
        return articleListResponse;
    }
}