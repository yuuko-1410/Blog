package com.yuuko.MyBlog.request.comment;

import lombok.Data;

@Data
public class CommentInfoRequest {
    //分页
    Integer current;
    Integer page;
    //状态
    Integer disable;
    //参数
    String pram;
    String Type;
    //参数
    String tid;
    String uid;
    String cid;
    String fromCid;
}
