package com.yuuko.MyBlog.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.yuuko.MyBlog.common.restful.R;
import com.yuuko.MyBlog.request.comment.CommentInfoRequest;
import com.yuuko.MyBlog.request.comment.CommentRequest;
import com.yuuko.MyBlog.response.comment.CommentListResponse;
import com.yuuko.MyBlog.service.BlogCommentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Api(value = "评论接口", tags = {"评论接口"})
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    BlogCommentService blogCommentService;

    /**
     * 添加评论
     *
     * @param commentRequest 发表评论请求
     * @return {@link R}
     */
    @SaCheckLogin
    @PostMapping("/setComment")
    R setComment(@RequestBody CommentRequest commentRequest){
        String msg = blogCommentService.addComment(commentRequest);
        return R.success(msg);
    }

    /**
     * 通过id筛选评论
     *
     * @param commentRequest 发表评论请求
     * @return {@link R}
     */
    @SaCheckLogin
    @PostMapping("/getCommonListById")
    R getCommonListById(@RequestBody CommentInfoRequest commentRequest){
        CommentListResponse commonListById = blogCommentService.getCommonListById(commentRequest);
        return R.success(commonListById);
    }

    @SaCheckLogin
    @PostMapping ("/delCommonByCid")
    R apiDelCommonByCid(@RequestBody CommentInfoRequest commentInfoRequest){
        String msg = blogCommentService.apiDelCommonByCid(commentInfoRequest);
        return R.success(msg);
    }
}
