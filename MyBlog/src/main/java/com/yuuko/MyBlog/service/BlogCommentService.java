package com.yuuko.MyBlog.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yuuko.MyBlog.domain.BlogComment;
import com.yuuko.MyBlog.request.comment.CommentInfoRequest;
import com.yuuko.MyBlog.request.comment.CommentRequest;
import com.yuuko.MyBlog.response.comment.CommentListResponse;

/**
* @author shibi
* @description 针对表【blog_comment】的数据库操作Service
* @createDate 2022-10-24 17:59:22
*/

public interface BlogCommentService extends IService<BlogComment> {
    String addComment(CommentRequest commentRequest);
    CommentListResponse getCommonListById(CommentInfoRequest commentInfoRequest);
    String apiDelCommonByCid(CommentInfoRequest commentInfoRequest);
}
