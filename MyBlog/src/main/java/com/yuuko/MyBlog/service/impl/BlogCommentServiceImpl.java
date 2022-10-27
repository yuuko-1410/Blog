package com.yuuko.MyBlog.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.yuuko.MyBlog.domain.BlogComment;
import com.yuuko.MyBlog.domain.BlogUser;
import com.yuuko.MyBlog.domain.vo.CommonUser;
import com.yuuko.MyBlog.mapper.BlogCommentMapper;

import com.yuuko.MyBlog.request.comment.CommentInfoRequest;
import com.yuuko.MyBlog.request.comment.CommentRequest;
import com.yuuko.MyBlog.response.comment.CommentListResponse;
import com.yuuko.MyBlog.service.BlogCommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author shibi
* @description 针对表【blog_comment】的数据库操作Service实现
* @createDate 2022-10-24 17:59:22
*/
@Service
public class BlogCommentServiceImpl extends ServiceImpl<BlogCommentMapper, BlogComment> implements BlogCommentService {

    @Autowired
    BlogCommentMapper blogCommentMapper;
    @Override
    public String addComment(CommentRequest commentRequest) {
        BlogComment blogComment = new BlogComment();
        BeanUtils.copyProperties(commentRequest,blogComment);
        blogCommentMapper.insert(blogComment);
        return "发送成功";
    }

    @Override
    public CommentListResponse getCommonListById(CommentInfoRequest commentInfoRequest) {
        MPJLambdaWrapper<BlogComment> blogCommentWrapper = new MPJLambdaWrapper<BlogComment>()
                .selectAll(BlogComment.class)
                .select(BlogUser::getUsername,BlogUser::getAvatarUrl)
                .leftJoin(BlogUser.class,BlogUser::getUid,BlogComment::getUid);

        //如果传入id，进行筛选
        if (!"".equals(commentInfoRequest.getUid())){
            blogCommentWrapper.eq(BlogComment::getUid,commentInfoRequest.getUid());
        }
        if (!"".equals(commentInfoRequest.getTid())){
            blogCommentWrapper.eq(BlogComment::getTid,commentInfoRequest.getTid());
        }
        if (!"".equals(commentInfoRequest.getFromCid())){
            blogCommentWrapper.eq(BlogComment::getFromCid,commentInfoRequest.getFromCid());
        }
        blogCommentWrapper.orderByDesc(BlogComment::getCreateTime);

        IPage<CommonUser> commonUserIPage = blogCommentMapper.selectJoinPage(
                new Page<>(commentInfoRequest.getCurrent(), commentInfoRequest.getPage()), CommonUser.class, blogCommentWrapper);
        List<CommonUser> commonUserList = commonUserIPage.getRecords();
        CommentListResponse commentListResponse = new CommentListResponse();
        commentListResponse.setCurrent(commentInfoRequest.getCurrent());
        commentListResponse.setPage(commentInfoRequest.getPage());
        commentListResponse.setSize((int)commonUserIPage.getTotal());
        commentListResponse.setCommentInfo(commonUserList);
        return commentListResponse;
    }

    @Override
    public String apiDelCommonByCid(CommentInfoRequest commentInfoRequest) {
        MPJQueryWrapper<BlogComment> commentMPJQueryWrapper = new MPJQueryWrapper<>();
        commentMPJQueryWrapper.eq("cid",commentInfoRequest.getCid());
        blogCommentMapper.delete(commentMPJQueryWrapper);
        return "操作成功";
    }
}