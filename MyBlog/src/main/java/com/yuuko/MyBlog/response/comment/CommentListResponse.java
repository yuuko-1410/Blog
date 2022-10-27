package com.yuuko.MyBlog.response.comment;

import com.yuuko.MyBlog.domain.vo.CommonUser;
import lombok.Data;

import java.util.List;

@Data
public class CommentListResponse {
    //分页
    private int current;
    private int page;
    private int size;
    List<CommonUser> commentInfo;
}
