package com.yuuko.MyBlog.request.comment;

import lombok.Data;

@Data
public class CommentRequest {
    private String tid;
    private String uid;
    private String fromCid;
    private String context;
}
